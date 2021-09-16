package com.malsum.inventoryservice.dao;

import com.malsum.inventoryservice.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {

    public Item getStockForItem(int id);
    public Item saveStockToItems(Item item);
    public List<Item> getStockFromList(List<Item> items);//pass a list and do a bulk search for items after map them into a map you i know for each item id how many do i have
    public Item removeItem(int id);
    public Map batchUpdate(Map<Integer,Integer> ids);
}
