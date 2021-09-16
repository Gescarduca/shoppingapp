package com.malsum.catalogservice.dao;

import com.malsum.catalogservice.entity.Brand;
import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.entity.ProductType;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ItemDaoImpl implements ItemDao{


    Logger logger = Logger.getLogger(Logger.class.getName());

    @Autowired
    EntityManager entityManager;

    @Override
    public Item getItem(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        System.out.println(id);
        Item item = currentSession.get(Item.class,id);
        System.out.println(item);
        return item;

    }

    @Override
    public Brand getBrand(String id) {
        Session currentSession = entityManager.unwrap(Session.class);
//        Brand brand = currentSession.get(Brand.class,id);
//        return brand;
        return entityManager.getReference(Brand.class,id);
    }
    @Override
    public ProductType getProductType(String id) {
        return entityManager.getReference(ProductType.class,id);
    }

    @Override
    public Boolean exists(Object o){
        Session currentSession = entityManager.unwrap(Session.class);
        //pass the object instead of string and do an instance of do query
        if (o instanceof Brand){
            return currentSession
                    .createQuery("select id from Brand where id =: id")
                    .setParameter("id",((Brand) o).getId()).uniqueResultOptional().isPresent();

        }else if(o instanceof ProductType) {
            return currentSession
                    .createQuery("select id from ProductType where id =: id")
                    .setParameter("id",((ProductType) o).getId()).uniqueResultOptional().isPresent();
        }
        return false;
    }

    //
    @Override
    public ProductType getItemsByProductType(String type) {
        Session currentSession = entityManager.unwrap(Session.class);
        ProductType productType = currentSession.get(ProductType.class,type);
        return productType;

    }
    @Override
    public Brand getItemsByBrand(String brand) {
        Session session = entityManager.unwrap(Session.class);
        Brand brand1 = session.get(Brand.class,brand);
        return brand1;
    }

    @Override
    public Item saveItem(Item item) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(item);
        System.out.println(item);
        return item;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(brand);
        return brand;
    }

    @Override
    public ProductType saveProductType(ProductType productType) {
        Session session = entityManager.unwrap(Session.class);
        System.out.println(productType + " in ");
        session.saveOrUpdate(productType);
        return productType;
    }

    @Override
    public void deleteItemFromCatalog(int id) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(session.get(Item.class,id));

    }

    @Override
    public List<Item> getItems(){
        Session session = entityManager.unwrap(Session.class);
        List<Item> items = session.createQuery("from Item order by id, product_type_id").getResultList();
        return items;
    }

    public void selectAll(){
        Session session = entityManager.unwrap(Session.class);
    }

    public Item findItemReference(int id){
        return entityManager.getReference(Item.class,id);//gets a reference to obecjt without actually loading the item
    }
}
