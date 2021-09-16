package com.malsum.inventoryservice.rest;

import com.malsum.inventoryservice.entity.Item;
import com.malsum.inventoryservice.entity.ItemWrapper;
import com.malsum.inventoryservice.exceptions.ItemNotFound;
import com.malsum.inventoryservice.entity.Wrapper;
import com.malsum.inventoryservice.service.ItemService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class RestControllerClass {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ItemService itemService;


    @Autowired
    Environment environment;

    @GetMapping("/{productId}")
    public ItemWrapper getItemStockById(@PathVariable("productId") int id){
        return itemService.getStockForItem(id);
    }

    @GetMapping("/productList")//personid for shopping session service
    //
    public List getListItemStock(@RequestBody List<ItemWrapper> list){
        List<ItemWrapper> items = itemService.getStockFromList(list).stream()
                .sorted(Comparator.comparingInt(ItemWrapper::getStock))
                .collect(Collectors.toList());

       return items;
        //on the other side will have to relate the string id to the actual items so i get the id the general info and the stock per item

    }


    @PostMapping("/add")
    public ItemWrapper addStock(@RequestBody ItemWrapper newItem){
        return itemService.saveStockToItems(newItem);
    }


    @PutMapping("/update/{productId}")
    public ItemWrapper updateStockItem(@RequestBody ItemWrapper updateItem, @PathVariable("productId") int id){
        return itemService.saveStockToItems(updateItem);
    }

    @DeleteMapping("/{productId}")
    public ItemWrapper removeStock(@PathVariable("productId") int id){
        ItemWrapper item = itemService.removeStock(id);
//        if (item==null){
//            throw new ItemNotFound("Item not found, id given was: " + id);
//        }
        return item;
    }


    @PutMapping("/update/list")
    public Map batchUpdate(@RequestBody Map<Integer,Integer> items){
        return itemService.batchUpdate(items);

    }






}
