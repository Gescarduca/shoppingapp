package com.malsum.shoppingsessionservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Scope(scopeName = "prototype")
public class SessionImplementation implements DisposableBean, Session {

    //@Id
    private int clientId;

    private Person person;

    //@OneToMany(fetch = FetchType.LAZY,mappedBy = "session",cascade = CascadeType.ALL)
    private List<ItemWrapper> items;

    //@Column(name = "cost")
    private double totalCostOfList;

//    @Transient
    @JsonIgnore
    private Map<Integer,Integer> itemQuantity;

//    public SessionImplementation(int clientId) {
//        this.clientId = clientId;
//        System.out.println(clientId);
//        this.items = new ArrayList<>();
//        this.itemQuantity = new HashMap<>();
//        System.out.println("in full one");
//    }

    public SessionImplementation(Person person){
        this.person = person;
        this.clientId= person.getId();
        this.items = new ArrayList<>();
        this.itemQuantity = new HashMap<>();
    }

    public SessionImplementation() {
        System.out.println("in empty constructor");
    }

    @Override
    public void addToMap(Integer id, Integer quantity){
        this.itemQuantity.put(id,quantity);
    }


    @Override
    public Person getPerson() {
        return person;
    }


    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @PostConstruct
    private void setPersonId(){
        System.out.println("in post construct");
        this.clientId = this.person.getId();
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    @Override
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public List<ItemWrapper> getItems() {
        return items;
    }

    @Override
    public void setItems(List<ItemWrapper> items) {
        this.items = items;
    }



    @Override
    public void setTotalCostOfList(double totalCostOfList) {
        this.totalCostOfList = totalCostOfList;
    }


//    @Override
//    public Map<Integer, Integer> getItemQuantity() {
//        return itemQuantity;
//    }
//
//
//    @Override
//    public void setItemQuantity(Map<Integer, Integer> itemQuantity) {
//        this.itemQuantity = itemQuantity;
//    }

    @Override
    public void addToList(ItemWrapper itemWrapper){
        this.items.add(itemWrapper);
    }

    @Override
    public ItemWrapper removeItem(int itemId){
        Optional<ItemWrapper> optional = this.getItems().stream().
                filter(itemWrapper -> itemId == itemWrapper.getId())
                .findAny();
        optional.ifPresent(itemWrapper -> this.items.remove(itemWrapper));
        return optional.get();
    }

    @Override
    public String toString() {
        return "Session{" +
                "clientId=" + clientId +
                ", person=" + person +
                ", items=" + items +
                ", totalCostOfList=" + totalCostOfList +
                ", itemQuantity=" + itemQuantity +
                '}';
    }


    @Override
    public void destroy() throws Exception {

    }
}


