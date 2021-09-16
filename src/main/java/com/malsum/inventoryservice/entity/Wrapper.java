package com.malsum.inventoryservice.entity;

import java.util.List;
import java.util.Map;

public class Wrapper {

    private Map<String,Integer> stock;
    private String string;
    private List<Item> items;

    public Wrapper() {
    }

    public Map<String, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<String, Integer> stock) {
        this.stock = stock;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
