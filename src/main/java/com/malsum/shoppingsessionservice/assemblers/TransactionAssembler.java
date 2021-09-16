package com.malsum.shoppingsessionservice.assemblers;

import com.malsum.shoppingsessionservice.entity.Item;
import com.malsum.shoppingsessionservice.entity.Transaction;
import com.malsum.shoppingsessionservice.models.ItemModel;
import com.malsum.shoppingsessionservice.models.TransactionModel;
import com.malsum.shoppingsessionservice.rest.SessionResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TransactionAssembler extends RepresentationModelAssemblerSupport<Transaction, TransactionModel> {

    @Autowired
    BeanFactory beanFactory;

    public TransactionAssembler() {
        super(SessionResource.class, TransactionModel.class);
    }

    @Override
    public TransactionModel toModel(Transaction entity) {
        TransactionModel transaction = instantiateModel(entity);
        //this should point to both self and need to add another link that goes to all transactions made this day, kinda like a receipt
        transaction.setId(entity.getId());
        transaction.setQuantity(entity.getQuantity());
        transaction.setItem(toModel(entity.getItemBought()));
        transaction.setValue(entity.getTransactionValue());
        transaction.setDate(entity.getDate());
        return transaction;
    }

    public ItemModel toModel(Item entity){
        ItemModel model = beanFactory.getBean(ItemModel.class);
        model.setCost(entity.getCost());
        model.setId(entity.getId());
        //changes this should point to catalog for user to get al info about this item
        //model.add(Link.of("Http://localhost:8765/{id}").expand(entity.getId()).withSelfRel());
        return model;
    }


}
