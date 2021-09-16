package com.malsum.shoppingsessionservice.service;

import com.malsum.shoppingsessionservice.assemblers.ItemAssembler;
import com.malsum.shoppingsessionservice.dao.ItemDao;
import com.malsum.shoppingsessionservice.entity.Item;
import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemAssembler itemAssembler;

    @Autowired
    ItemDao itemDao;

    public boolean save(ItemWrapper itemWrapper){
        itemDao.save(itemAssembler.changeToItem(itemWrapper));
        return true;
    }

}
