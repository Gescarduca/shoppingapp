package com.malsum.catalogservice.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.malsum.catalogservice.views.ShopViews;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;

//@JsonFilter("userFilter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Scope(value = "prototype")
public class ItemWrapper {
    private int id;
    @NotEmpty(message = "Please provide a name")
    private String name;
    @NotEmpty(message = "Please provide a brand")
    private String brand;
    @NotEmpty(message = "Please provide a product type")
    private String type;
    @NotNull
    @JsonView(ShopViews.Professional.class)
    private int stock;
    @NotEmpty(message = "Please provide a description")
    private String description;
    @JsonView(ShopViews.Professional.class)
    private String environment;
    @NotEmpty(message = "Please provide a product cost")
    private double cost;

//    public ItemWrapper(int id, String name, String type, String brand, String description) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.brand = brand;
//        this.description = description;
//    }




//    public ItemWrapper(int id, String name, String type, String brand, String productType, int stock, String description, double cost) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.brand = brand;
//        this.productType = productType;
//        this.stock = stock;
//        this.description = description;
//        this.cost = cost;
//    }
//
//    public ItemWrapper() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getEnvironment() {
//        return environment;
//    }
//
//    public void setEnvironment(String environment) {
//        this.environment = environment;
//    }
//
//    public String getUri() {
//        return uri;
//    }
//
//    public void setUri(String uri) {
//        this.uri = uri;
//    }
//
//    public double getCost() {
//        return cost;
//    }
//
//    public void setCost(double cost) {
//        this.cost = cost;
//    }
//
//    public String getProductType() {
//        return productType;
//    }
//
//    public void setProductType(String productType) {
//        this.productType = productType;
//    }

}
