package com.malsum.shoppingsessionservice.models;

import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ItemModel extends RepresentationModel<ItemModel> {
    private int id;
    private double cost;
    private String name;

//    public ItemModel(ItemWrapper itemWrapper) {
//        this.id = itemWrapper.getId();
//        this.cost=itemWrapper.getCost();
//    }

    public ItemModel(ItemWrapper itemWrapper,String name) {
        this.id = itemWrapper.getId();
//        this.cost=itemWrapper.getCost();
        this.name = name;
    }
    public ItemModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
