package com.malsum.inventoryservice.dao;

import com.malsum.inventoryservice.entity.Item;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Repository
public class ItemDaoImpl implements ItemDao{


    @Autowired
    EntityManager entityManager;

    Logger myLogger = Logger.getLogger(Logger.getGlobal().getName());

    @Autowired
    public ItemDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Item getStockForItem(int id) {
        Session session = entityManager.unwrap(Session.class);
        myLogger.info(String.valueOf(id));
        return session.get(Item.class,id);
    }

    @Override
    public Item saveStockToItems(Item item) {
        Session session = entityManager.unwrap(Session.class);
        Item oldItem = session.get(Item.class,item.getId());
        if (!(oldItem==null)){
            oldItem.setStock(oldItem.getStock()+ item.getStock());
            session.saveOrUpdate(oldItem);
            return oldItem;
        }else {
            session.saveOrUpdate(item);
            return item;
        }
    }

    @Override
    public List<Item> getStockFromList(List<Item> items) {
        //though this might work must look for a more efficient solution seems very inelegant
        List<Integer> ids = new ArrayList<>();
        items.forEach(item -> ids.add(item.getId()));
//        for (Item i : items){
//            ids.add(i.getId());
//        }

        Session session = entityManager.unwrap(Session.class);
        MultiIdentifierLoadAccess<Item> multiIdentifierLoadAccess = session.byMultipleIds(Item.class);
        return multiIdentifierLoadAccess.multiLoad(ids);
    }

    @Override
    public Item removeItem(int id) {
        Session session = entityManager.unwrap(Session.class);
        Item itemClass = session.get(Item.class,id);
        if (itemClass == null){
            return null;
        }
        session.delete(itemClass);
        return itemClass;
    }

    @Override
    public Map batchUpdate(Map<Integer,Integer> ids) {
        Session currentSession =  entityManager.unwrap(Session.class);
        MultiIdentifierLoadAccess<Item> multiIdentifierLoadAccess = currentSession.byMultipleIds(Item.class);
        List<Item> oldItems = multiIdentifierLoadAccess.multiLoad(new ArrayList<>(ids.keySet()));
        for (Item i : oldItems){
            i.setId(i.getStock()- ids.get(i.getId()));
            currentSession.saveOrUpdate(i);
        }
        return ids;
    }
}
