package com.malsum.inventoryservice.service;

import com.malsum.inventoryservice.dao.ItemDao;
import com.malsum.inventoryservice.entity.Item;
import com.malsum.inventoryservice.entity.ItemWrapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDao itemDao;

    @Autowired
    BeanFactory beanFactory;

    @Override
    @Transactional
    public ItemWrapper getStockForItem(int id) {
        ItemWrapper itemWrapper = changeToItemWrapper(itemDao.getStockForItem(id));
        return itemWrapper;
    }

    @Override
    @Transactional
    public ItemWrapper saveStockToItems(ItemWrapper item) {
        System.out.println(item+"in service");
        return changeToItemWrapper(itemDao.saveStockToItems(changeToItem(item)));
    }

    @Override
    @Transactional
    public List<ItemWrapper> getStockFromList(List<ItemWrapper> items) {
        return itemDao.getStockFromList(items.stream().map(this::changeToItem).collect(Collectors.toList()))
                .stream().map(this::changeToItemWrapper).collect(Collectors.toList());
    }

    @Override
    @Transactional
        public ItemWrapper removeStock(int itemToDelete) {
        return changeToItemWrapper(itemDao.removeItem(itemToDelete));
    }

    @Override
    public Map batchUpdate(Map<Integer, Integer> items) {
        return itemDao.batchUpdate(items);

    }

    private ItemWrapper changeToItemWrapper(Item item){
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);
        itemWrapper.setId(item.getId());
        itemWrapper.setStock(item.getStock());
        return itemWrapper;
    }

    private Item changeToItem(ItemWrapper itemWrapper){
        Item item = beanFactory.getBean(Item.class);
        item.setId(itemWrapper.getId());
        item.setStock(itemWrapper.getStock());
        return item;
    }
}
