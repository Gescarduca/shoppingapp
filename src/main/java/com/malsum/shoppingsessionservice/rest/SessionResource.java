package com.malsum.shoppingsessionservice.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsum.shoppingsessionservice.assemblers.ItemAssembler;
import com.malsum.shoppingsessionservice.assemblers.TransactionAssembler;
import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import com.malsum.shoppingsessionservice.entity.Session;
import com.malsum.shoppingsessionservice.entity.SessionHandler;
import com.malsum.shoppingsessionservice.entity.Transaction;
import com.malsum.shoppingsessionservice.exceptions.NotEnoughStockException;
import com.malsum.shoppingsessionservice.models.ItemModel;
import com.malsum.shoppingsessionservice.proxy.CatalogProxy;
import com.malsum.shoppingsessionservice.proxy.InventoryProxy;
import com.malsum.shoppingsessionservice.models.TransactionModel;
import com.malsum.shoppingsessionservice.service.SessionService;
import com.malsum.shoppingsessionservice.service.TestingService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SessionResource {

    //add stuff to shoppingcart
    //commit to selling the items and consuming inventory service to change stock
    //save what what was bought and total value to database
    //return items currently in list session
    Logger myLogger = Logger.getLogger(Logger.getGlobal().getName());

    @Autowired
    SessionService sessionService;

    @Autowired
    CatalogProxy catalogProxy;

    @Autowired
    InventoryProxy inventoryProxy;

    @Autowired
    SessionHandler sessionHandler;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ItemAssembler assembler;

    @Autowired
    TestingService testingService;

    @Autowired
    TransactionAssembler transactionAssembler;

    @PostMapping("/{id}/items")
//    public HttpStatus addItem(ItemWrapper itemWrapper,Integer quantity,@PathVariable int id){//person id
    public RepresentationModel addItem(@RequestBody @NonNull String json, @PathVariable("id") int id,
                                       HttpServletRequest servletRequest,@RequestHeader("Host") String header)
            throws JsonProcessingException {//person id
        //check Session handler for existing session
        //add to existing, if null create new

        System.out.println(header);

        JsonNode jsonNode = new ObjectMapper().readTree(json);
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);

        itemWrapper.setId(jsonNode.get("id").asInt());
        itemWrapper.setQuantity(jsonNode.get("quantity").asInt());

        ItemWrapper invProxyItem = inventoryProxy.getItemStockById(itemWrapper.getId());

        if (itemWrapper.getQuantity() > invProxyItem.getStock()){
            throw new NotEnoughStockException();
        }

        //problem this database saves cost and item id , feels inneficient since i always have to ask cat service for item name,
        // plus, should request come with name and id or
        //just id?depends on the frontend i guess but which would be the best choice
        //solution 1: for now call cat service for name since it might be more than name that we would want to show in a real app
        ItemModel model = sessionService.addItem(itemWrapper, id);

        //get info from catalog service if user wants more info he may click the self link
        model.setName(catalogProxy.getItem(model.getId()).getName());
        System.out.println(model.getId());
        model.add(Link.of("http://localhost:8765/catalog/{id}").expand(model.getId()).withSelfRel());


            return RepresentationModel.of(model);
        }



    @GetMapping("/{id}/cart")
    public List<TransactionModel> commitSale(@PathVariable("id") int id){

        //i need to return collection of items bought and total value of this collections of transactions

        List<Transaction> list = sessionService.saveAll(id);
       List<TransactionModel> l = list.stream()
                .map(transaction -> transactionAssembler.toModel(transaction))
               .collect(Collectors.toList());

               l.forEach(transaction -> {
            ItemModel model = transaction.getItem();
            model.setName(catalogProxy.getItem(model.getId()).getName());
            model.add(Link.of("http://localhost:8765/catalog/{id}").expand(model.getId()).withSelfRel());
            transaction.add(Link.of("http://localhost:8765/sales/{id}/order/{id}").expand(id,transaction.getId()));
        });

        //still need to terminate a session bean
        return l;
    }

    @GetMapping("/test")
    public Map<Integer,Session> getSessions(){
        return sessionHandler.getPeopleInSession();
    }

    @GetMapping("{id}/items")
    public CollectionModel<ItemModel> getSessionItems(@PathVariable("id") int personId){
        List<ItemModel> list =  sessionService.currentItemList(personId);
        list.stream().forEach(itemModel -> {
            itemModel.setName(catalogProxy.getItem(itemModel.getId()).getName());
            itemModel.add(Link.of("http://localhost:8765/{id}/items").expand(itemModel.getId()).withSelfRel());//where should this link to?catalog to get info about the product?
        });
        CollectionModel<ItemModel> result = CollectionModel.of(list,Link.of("http://localhost:8765/sales/{id}/items").expand(personId).withSelfRel());
        return result;
    }

    @DeleteMapping("/{id}/{id}")
    public ResponseEntity removeFromSession(@PathVariable("id") int personId,@PathVariable("id") int itemId){

        //remove item from a session
        return new ResponseEntity(sessionService.removeItem(personId, itemId), HttpStatus.OK);
    }


    @GetMapping("/transactions/{id}/{startDate}/{endDate}")
    public CollectionModel<TransactionModel> getTransactionsByDate(@PathVariable("id") int personId,
                                                                   @PathVariable("startDate") @DateTimeFormat String startDate,
                                                                   @PathVariable("endDate") @DateTimeFormat String endDate)
    {



        myLogger.info(startDate + "======" + endDate);
        String startDate2 = "2021-03-01";
        String endDate2 = "2021-04-08";

        List<TransactionModel> records = sessionService.getRecords(startDate2, endDate2, personId).stream()
                .map(transaction -> transactionAssembler.toModel(transaction))
                .collect(Collectors.toList());

        records.forEach(transactionModel -> {
            ItemModel model = transactionModel.getItem();
            model.setName(catalogProxy.getItem(model.getId()).getName());
            model.add(Link.of("http://localhost:8765/catalog/{id}").expand(model.getId()).withSelfRel());
            transactionModel.add
                    (Link.of("http://localhost:8765/sales/{id}/transactions/{transactionId}").expand(personId,transactionModel.getId()).withSelfRel());
        });

        return CollectionModel.of(records,Link.of("http://localhost:8765/sales/{id}/transactions/{startDate}/{endDate}")
                //.expand(Map.of("id",personId),"startDate",startDate,"endDate",endDate).withSelfRel());
                .expand(personId,startDate,endDate).withSelfRel());
    }

    @GetMapping("/all")
    public List<TransactionModel> getAllTransactions(){
        return sessionService.getAllTransactions().stream()
                .map(transaction -> transactionAssembler.toModel(transaction))
                .collect(Collectors.toList());
    }

    //getallTransactions



}
