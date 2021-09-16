package com.malsum.shoppingsessionservice.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "item")
@Component
@Scope(value = "prototype")
public class Item {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "cost")
    private double cost;//cost exists here because i thought it was wrong to query catalog each time i wanted a history of items bought in the past

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "itemBought",cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Transaction> transactions;

    public Item(int id, double cost) {
        this.id = id;
        this.cost = cost;
    }

    public Item() {
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


}
