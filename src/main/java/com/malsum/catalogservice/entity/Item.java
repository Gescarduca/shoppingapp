package com.malsum.catalogservice.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "item")
@Component
@Scope(value = "prototype")
public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//prob gonna change later to UUID as Varchar
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//    @Transient
//    @JsonIgnore
//    private int stock;


    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "product_type_id")
    //@JsonManagedReference
    private ProductType productType;


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "brand_id")
    //@JsonBackReference
    //@JsonManagedReference

    private Brand brandId;

    @Column(name = "cost")
    private double cost;

    public Item() {

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


//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productType=" + productType +
                ", brandId=" + brandId +
                '}';
    }

//    public void map(ItemWrapper itemWrapper){
//        this.id = itemWrapper.getId();
//        this.description = itemWrapper.getDescription();
//        this.stock = itemWrapper.getStock();
//        this.name=itemWrapper.getName();
//        this.productType=
//    }
}
