package com.malsum.inventoryservice.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ItemWrapper {
    private int id;
    private int stock;




    public ItemWrapper() {
    }


    public ItemWrapper(int id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ItemWrapper{" +
                "id=" + id +
                ", stock=" + stock +
                '}';
    }
}
