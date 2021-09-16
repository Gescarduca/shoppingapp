package com.malsum.inventoryservice.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@Component
@Scope(value = "prototype")
public class Item {
    @Id
    @Column(name = "id")
    private int id;//id will be generated in another service that will then pass the item object to other services so they can add to their specific DBs
    /*
    @Column(name = "name")
    private String name;
    */

    @Column(name = "stock")
    private int stock;

    public Item() {
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

//    @Override
//    public String toString() {
//        return "Item{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", stock=" + stock +
//                '}';
//    }
}
