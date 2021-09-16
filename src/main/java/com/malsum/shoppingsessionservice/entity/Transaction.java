package com.malsum.shoppingsessionservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "transactions")
@Component
@Scope(value = "prototype")
public class Transaction{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn( name = "item_id")
    private Item itemBought;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private String date;

    //to be filled with a trigger in database
    @Column(name = "value")
    private double transactionValue;


    public Transaction(Item itemBought, int quantity,Person person) {
        this.itemBought = itemBought;
        this.quantity = quantity;
        this.person=person;
        setDate();
    }

    public Transaction() {
    }

        private void setDate(){
        this.date = LocalDate.now().toString();
    }
//
//    private void setDate(){
//        this.date = Date.valueOf(LocalDate.now());
//    }


        public String getDate() {
        return date;
    }

    @PostConstruct
    private void setValue(){
        this.transactionValue = quantity*itemBought.getCost();
    }
}
