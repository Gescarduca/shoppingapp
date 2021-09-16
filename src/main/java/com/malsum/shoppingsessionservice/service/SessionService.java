package com.malsum.shoppingsessionservice.service;

import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import com.malsum.shoppingsessionservice.entity.Session;
import com.malsum.shoppingsessionservice.entity.Transaction;
import com.malsum.shoppingsessionservice.models.ItemModel;
import com.malsum.shoppingsessionservice.models.TransactionModel;

import java.util.List;

public interface SessionService {
    public List<ItemModel> currentItemList(int id);
    public List saveAll(int personId);
    public ItemModel addItem(ItemWrapper itemWrapper, int id);
    public ItemModel removeItem(int personId,int itemId);
    public List<Transaction> getRecords(String startDate, String endDate,int personId);
    public List<Transaction> getAllTransactions();



}
