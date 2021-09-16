package com.malsum.shoppingsessionservice.service;

import com.malsum.shoppingsessionservice.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestingService {

    @Autowired
    ItemDao itemDao;

    public Boolean exists(){
        return itemDao.existsById(18);
    }
}
