package com.malsum.shoppingsessionservice.proxy;

import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "catalog-service")
@RequestMapping("/catalog")
public interface CatalogProxy {

    @GetMapping("/items/{id}")
    public ItemWrapper getItem(@PathVariable("id") int id);
}
