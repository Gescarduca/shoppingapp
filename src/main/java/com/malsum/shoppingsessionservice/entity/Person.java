package com.malsum.shoppingsessionservice.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@Component
@Scope(scopeName = "prototype")
public class Person {

    @Id
    @Column(name = "person_id")
    private int id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "person",cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Transaction> transactions;


    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
