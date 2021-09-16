package com.malsum.shoppingsessionservice.rest;


import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import com.malsum.shoppingsessionservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/pro")
public class ShopRest {

    @Autowired
    ItemService itemService;


    Logger myLogger = Logger.getLogger(Logger.getGlobal().getName());
    @PostMapping("/saveItem")
    public HttpStatus save(@RequestBody ItemWrapper newItem){
        myLogger.info(newItem.toString());
        itemService.save(newItem);
        return HttpStatus.CREATED;
    }
}
