package com.malsum.catalogservice.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "brand")
@Component
@Scope(value = "prototype")
public class Brand {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id")
    private String id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "brandId",cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<Item> itemsProduced;



    public Brand() {
    }

    public List<Item> getItemsProduced() {
        return itemsProduced;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItemsProduced(List<Item> itemsProduced) {
        this.itemsProduced = itemsProduced;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + id;
    }

//    @Id
//    @GeneratedValue(generator = “UUID”)
//    @GenericGenerator(
//            name = “UUID”,
//            strategy = “org.hibernate.id.UUIDGenerator”,
//    )
//    @Column(name = “id”, updatable = false, nullable = false)
//    private UUID id;
}
