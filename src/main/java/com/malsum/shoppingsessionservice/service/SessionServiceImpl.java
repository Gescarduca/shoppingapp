package com.malsum.shoppingsessionservice.service;

import com.malsum.shoppingsessionservice.assemblers.ItemAssembler;
import com.malsum.shoppingsessionservice.assemblers.TransactionAssembler;
import com.malsum.shoppingsessionservice.dao.ItemDao;
import com.malsum.shoppingsessionservice.dao.PersonDao;
import com.malsum.shoppingsessionservice.dao.TransactionDao;
import com.malsum.shoppingsessionservice.entity.*;
import com.malsum.shoppingsessionservice.exceptions.NotFoundException;
import com.malsum.shoppingsessionservice.models.ItemModel;
import com.malsum.shoppingsessionservice.models.TransactionModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService{
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    SessionHandler sessionHandler;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ItemDao itemDao;

    @Autowired
    TransactionAssembler assembler;

    @Autowired
    ItemAssembler itemAssembler;

    Logger myLogger = Logger.getLogger(Logger.class.getName());
    @Override
    public List<ItemModel> currentItemList(int id) {
        return sessionHandler.getSession(id).getItems().stream().map(itemWrapper -> {
             ItemModel model = itemAssembler.changeToItemModel(itemWrapper);
             model.setCost(itemDao.getCost(model.getId()));//should this be here or on item assembler?
             return model;
        }).collect(Collectors.toList());
    }


    public ItemModel addItem(ItemWrapper itemWrapper, int id){

        Optional<Item> optional = itemDao.findById(itemWrapper.getId());
        if (optional.isEmpty()){
            throw new NotFoundException("Item not found");
        }

//        if (!(sessionHandler.getPeopleInSession().containsKey(id))){
//            throw new NotFoundException("User not found");
//        }


        ItemModel model = itemAssembler.changeToItemModel(itemWrapper);
        optional.ifPresent(item -> model.setCost(item.getCost()));

        Session session = sessionHandler.getSession(id);
        if (session==null){//create new session
            myLogger.info(String.valueOf(id));

            //session=beanFactory.getBean(SessionImplementation.class,id);
            Optional<Person> person = personDao.findById(id);
            session = beanFactory.getBean(Session.class, person.get());
            sessionHandler.add(session);
        }

        session.addToList(itemWrapper);

        return model;
    }

    @Override
    public List<Transaction> saveAll(int personId) {

        //less save more commit


        List<Transaction> list = new ArrayList();
        Session session = sessionHandler.getSession(personId);
        session.getItems().forEach(itemWrapper -> {
            list.add(createTransaction(itemWrapper,personId));
        });

        myLogger.info(session.getPerson().toString());

        transactionDao.saveAll(list).forEach(list::add);
        return list;
    }

    @Override
    public ItemModel removeItem(int personId,int itemId){
        return itemAssembler.changeToItemModel(sessionHandler.getSession(personId).removeItem(itemId));
    }

    @Override
    public List<Transaction> getRecords(String startDate, String endDate, int personId) {
        return transactionDao.getTransactionRecords(startDate,endDate,personId).stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .collect(Collectors.toList());
//        List<TransactionModel> transactionModels = transactionDao.getTransactionRecords(personId).stream()
//                .map(transaction -> assembler.toModel(transaction))
//                .sorted(Comparator.comparing(TransactionModel::getDate))
//                .collect(Collectors.toList());
//        return transactionModels;
    }


    @Override
    public List<Transaction> getAllTransactions(){
        return transactionDao.transactions();
    }


    private Transaction createTransaction(ItemWrapper itemWrapper,int personId) {
        //item needs to exist  and person needs to exist
        //if person does not exist get person from session
        Person person;
        Item item;

        if (itemDao.existsById(itemWrapper.getId())){
            item= itemDao.findById(itemWrapper.getId()).get();
        }else {
            //need to change this
            item = changeToItem(itemWrapper);
        }
        if (personDao.existsById(personId)){
            person = personDao.findById(personId).get();
        }else {
            person = sessionHandler.getSession(personId).getPerson();
        }
        return beanFactory.getBean(Transaction.class,item,itemWrapper.getQuantity(),person);
    }






    public Item changeToItem(ItemWrapper itemWrapper){
        Item item = beanFactory.getBean(Item.class);
        item.setId(itemWrapper.getId());
        item.setCost(itemWrapper.getCost());
        return item;
    }
    public ItemWrapper changeToItemWrapper(Item item, Transaction transaction){
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);
        itemWrapper.setId(item.getId());
        itemWrapper.setCost(item.getCost());
        itemWrapper.setQuantity(transaction.getQuantity());
        return itemWrapper;
    }
}
