package com.malsum.inventoryservice.service;

import com.malsum.inventoryservice.entity.Item;
import com.malsum.inventoryservice.entity.ItemWrapper;

import java.util.List;
import java.util.Map;

public interface ItemService {
    public ItemWrapper getStockForItem(int id);
    public ItemWrapper saveStockToItems(ItemWrapper itemWrapper);
    public List<ItemWrapper> getStockFromList(List<ItemWrapper> items);//pass a list and do a bulk search for items after map them into a map you i know for each item id how many do i have
    public ItemWrapper removeStock(int id);
    public Map batchUpdate(Map<Integer,Integer> items);
}
