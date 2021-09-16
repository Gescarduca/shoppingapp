package com.malsum.catalogservice.proxys;

import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.entity.ItemWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@FeignClient(name = "inventory-service",url = "localhost:8000/inventory")
@FeignClient(name = "inventory-service")
@RequestMapping("inventory")
public interface InventoryProxy {

    @PostMapping("/add")
    public ItemWrapper addStock(@RequestBody ItemWrapper newItem);//this should only allowed for employee role

    @PutMapping("/update/{productId}")
    public Boolean updateStockItem(@RequestBody ItemWrapper updateItem,@PathVariable("productId") int id);//this should only allowed for employee role

    @DeleteMapping("/{productId}")
    public boolean removeStock(@PathVariable("productId") int id);//this should only allowed for employee role

    @GetMapping("/{productId}")
    public Item getItemStockById(@PathVariable("productId") int id);//this should only allowed for employee role


}
