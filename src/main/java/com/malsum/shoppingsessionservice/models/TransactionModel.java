package com.malsum.shoppingsessionservice.models;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TransactionModel extends RepresentationModel<TransactionModel> {


    private int id;
    private ItemModel item;
    private double value;
    private int quantity;
    private String date;

    public TransactionModel() {
    }

    public TransactionModel(int id,ItemModel item, double value, int quantity, String date) {
        this.item = item;
        this.value = value;
        this.quantity = quantity;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", item=" + item +
                ", value=" + value +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                '}';
    }
}
