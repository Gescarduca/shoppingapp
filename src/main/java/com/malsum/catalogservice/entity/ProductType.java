package com.malsum.catalogservice.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_type")
@Component
@Scope(value = "prototype")
public class ProductType {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "product_type_id")
    private String id;



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "productType", cascade = CascadeType.ALL)
    private List<Item> items;

    public ProductType(String id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public ProductType() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "type='" + id + '\'' +
                '}';
    }
}
