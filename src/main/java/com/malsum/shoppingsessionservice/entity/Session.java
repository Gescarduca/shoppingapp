package com.malsum.shoppingsessionservice.entity;

import java.util.List;
import java.util.Map;

public interface Session {

    void addToMap(Integer id, Integer quantity);

    Person getPerson();

    void setPerson(Person person);

    int getClientId();

    void setClientId(int clientId);

    List<ItemWrapper> getItems();

    void setItems(List<ItemWrapper> items);

    void setTotalCostOfList(double totalCostOfList);

    //Map<Integer, Integer> getItemQuantity();

    //void setItemQuantity(Map<Integer, Integer> itemQuantity);

    void addToList(ItemWrapper itemWrapper);

    ItemWrapper removeItem(int itemId);

    @Override
    String toString();
}
