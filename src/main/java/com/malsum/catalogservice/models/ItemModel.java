package com.malsum.catalogservice.models;

import com.malsum.catalogservice.entity.ItemWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
//ItmModel implementation depends on the service providing a copy of it
public class ItemModel extends RepresentationModel<ItemModel> {
    private int id;
    private double cost;
    private String name;
    private String productType;
    private String brand;

//    public ItemModel(ItemWrapper itemWrapper, String name) {
//        this.id = itemWrapper.getId();
//        this.cost=itemWrapper.getCost();
//        this.name = name;
//    }


}
