package com.malsum.catalogservice.proxys;

import com.malsum.catalogservice.entity.ItemWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shopping-service")
@RequestMapping("/pro")
public interface ShoppingProxy {

    @PostMapping("/saveItem")
    public HttpStatus save(@RequestBody ItemWrapper newItem);
}
