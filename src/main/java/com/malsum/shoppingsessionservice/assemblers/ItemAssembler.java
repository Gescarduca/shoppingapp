package com.malsum.shoppingsessionservice.assemblers;

import com.malsum.shoppingsessionservice.entity.Item;
import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import com.malsum.shoppingsessionservice.entity.Transaction;
import com.malsum.shoppingsessionservice.models.ItemModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemAssembler {

    @Autowired
    BeanFactory beanFactory;

    public Item changeToItem(ItemWrapper itemWrapper){
        Item item = beanFactory.getBean(Item.class);
        item.setId(itemWrapper.getId());
        item.setCost(itemWrapper.getCost());
        return item;
    }


    public ItemWrapper changeToItemWrapper(Item item, Transaction transaction){
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);
        itemWrapper.setId(item.getId());
        itemWrapper.setQuantity(transaction.getQuantity());
        return itemWrapper;
    }

    public ItemModel changeToItemModel(ItemWrapper item){
        ItemModel model = beanFactory.getBean(ItemModel.class);
        model.setId(item.getId());
        Optional<String> name = Optional.ofNullable(item.getName());
        if (name.isPresent()){
            model.setName(item.getName());
        }
        return model;
    }
}
