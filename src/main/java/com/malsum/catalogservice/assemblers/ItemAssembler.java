package com.malsum.catalogservice.assemblers;

import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.entity.ItemWrapper;
import com.malsum.catalogservice.models.ItemModel;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemAssembler {

    @Autowired
    BeanFactory beanFactory;

    public ItemModel toModel(ItemWrapper itemWrapper){
        ItemModel model = beanFactory.getBean(ItemModel.class);
        model.setName(itemWrapper.getName());
        model.setCost(itemWrapper.getCost());
        model.setId(itemWrapper.getId());
        model.setBrand(itemWrapper.getBrand());
        model.setProductType(itemWrapper.getType());
        return model;
    }

    public ItemWrapper toWrapper(Item item){
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);
        itemWrapper.setId(item.getId());
        itemWrapper.setCost(item.getCost());
        itemWrapper.setDescription(item.getDescription());
        itemWrapper.setBrand(item.getBrandId().getId());
        itemWrapper.setName(item.getName());
        itemWrapper.setType(item.getProductType().getId());
        return itemWrapper;
    }
}
