package com.malsum.shoppingsessionservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.malsum.shoppingsessionservice.views.ItemViews;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ItemWrapper{
    private int id;
    private int stock;
    private int quantity;
    private double cost;
    private String name;



    public ItemWrapper(Item item){
        this.id = item.getId();
    }

    public ItemWrapper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ItemWrapper{" +
                "id=" + id +
                ", stock=" + stock +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
