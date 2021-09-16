package com.malsum.shoppingsessionservice.proxy;


import com.malsum.shoppingsessionservice.entity.Item;
import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@FeignClient(name = "inventory-service",url = "localhost:8000/inventory")
@FeignClient(name = "inventory-service")
@RequestMapping("/inventory")
public interface InventoryProxy {

//    @PostMapping("/add")
//    public Boolean addStock(@RequestBody ItemWrapper newItem);//this should only allowed for employee role

    @PutMapping("/update/{productId}")
    public Boolean updateStockItem(@RequestBody ItemWrapper updateItem,@PathVariable("productId") int id);//this should only allowed for employee role

//    @DeleteMapping("/{productId}")
//    public boolean removeStock(@PathVariable("productId") int id);//this should only allowed for employee role

    @GetMapping("/{productId}")
    public ItemWrapper getItemStockById(@PathVariable("productId") int id);//this should only allowed for employee role

    @PutMapping("/update/list")
    public boolean batchUpdate(@RequestBody Map<Integer,Integer> items);
}
