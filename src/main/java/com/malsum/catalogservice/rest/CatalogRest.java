package com.malsum.catalogservice.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.malsum.catalogservice.assemblers.ItemAssembler;
import com.malsum.catalogservice.entity.*;
import com.malsum.catalogservice.models.ItemModel;
import com.malsum.catalogservice.proxys.InventoryProxy;
import com.malsum.catalogservice.proxys.ShoppingProxy;
import com.malsum.catalogservice.service.ItemService;
import com.malsum.catalogservice.views.ShopViews;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/catalog")
public class CatalogRest {


    Logger myLogger = Logger.getLogger(getClass().getName());

    @Autowired
    private InventoryProxy inventoryProxy;

//    @Autowired
//    ShoppingProxy shoppingProxy;
    @Autowired
    ItemService itemService;


    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ShoppingProxy shoppingProxy;

    @Autowired
    Environment environment;

    @Autowired
    ItemAssembler itemAssembler;

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/items/{id}")
    public RepresentationModel getItem(@PathVariable("id") int id){
        //if you want to bot send the item and the location to get all items
       // Item itemToReturn = itemService.getItem(id)


//        Item item = itemService.getItem(id);
//        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class,item.getId(),item.getName(),
//                item.getProductType().getId(),item.getBrandId().getId(), item.getDescription());

        ItemModel model = itemAssembler.toModel(itemService.getItem(id));

        model.add(WebMvcLinkBuilder.linkTo(CatalogRest.class).slash(model.getId()).withSelfRel());
//        itemWrapper.setEnvironment(environment.getProperty("local.server.port"));
//        String uri = UriComponentsBuilder.newInstance().scheme("http")
//                .host("localhost:8765")
//                .path("/catalog/items").buildAndExpand(itemWrapper.getId()).toUriString();
//        itemWrapper.setUri(uri);


//        return itemWrapper;
        return model;
    };

    @GetMapping(value = "/items", produces = "application/vnd.app-v1+json")
    public CollectionModel getItems(){
        List<ItemModel> items = itemService.getItems().stream()
                .sorted(Comparator.comparing(ItemWrapper::getId))
                .map(itemModel -> itemAssembler.toModel(itemModel))
                .collect(Collectors.toList());
        items.forEach(itemModel -> itemModel.add((WebMvcLinkBuilder.linkTo(CatalogRest.class).slash(itemModel.getId()).withSelfRel())));

//        List<ItemWrapper> items = itemService.getItems();
//        String uri;
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
////        for (ItemWrapper i : items) {
////            uri = UriComponentsBuilder.newInstance().scheme("http")
////                    .host("localhost:8765")
////                    .path("/catalog/items/{id}").buildAndExpand(i.getId()).toUriString();
////            i.setUri(uri);
////
////        }

//        String s = objectMapper.writerWithView(ShopViews.User.class).writeValueAsString(items);
////        String s = objectMapper.writerWithView(ShopViews.User.class).writeValueAsString(items2);
//        return new ResponseEntity(s, HttpStatus.ACCEPTED);
        return CollectionModel.of(items,WebMvcLinkBuilder.linkTo(CatalogRest.class).withSelfRel());
    }




    //only for members of the store implement authentication and user roles later

    @PostMapping("/items")
    public ItemWrapper saveItem(@RequestBody String json) {
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);
        try{
            JsonNode itemJson = new ObjectMapper().readTree(json);
            itemWrapper.setName(itemJson.get("name").asText());

            //get stock professional wants to add to pass later to inventory service
            itemWrapper.setStock(Integer.parseInt(itemJson.get("stock").asText()));

            //get cost to send to shopping service
            itemWrapper.setCost(Double.parseDouble(itemJson.get("cost").asText()));

            //get names of brand and product type
            itemWrapper.setBrand(itemJson.get("brand").asText());
            //itemWrapper.setType(itemJson.get("type").asText());
            itemWrapper.setDescription(itemJson.get("description").asText());


        }catch (JsonProcessingException jsonProcessingException){
            //custom exception for user and log the actual exception for dev
        }
        //save object logic in itemservice
        ItemWrapper itemWrapper1 = itemService.saveItem(itemWrapper);

        //build uri to update this item in case use made a mistake
        String uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost:8765")
                .path("/items/{id}").buildAndExpand(itemWrapper1.getId()).toUriString();
        //itemWrapper1.setUri(uri);

        //send to inventory service, perhaps its a good idea to create a service only for pros that send request to catalog for info and inventory and writes to both
        //when adding items
        inventoryProxy.addStock(itemWrapper1);

        //send to shopping to register new possible item to be bought
        //must be after save to get id
        shoppingProxy.save(itemWrapper1);

        return itemWrapper1;
    }

    @PostMapping(value = "/items2", produces = "application/vnd.app-v2+json")
    public RepresentationModel saveItemVersion2(@Valid @RequestBody ItemWrapper itemWrapper){
        myLogger.info(itemWrapper.toString());
        ItemModel model = itemAssembler.toModel(itemService.saveItem(itemWrapper));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CatalogRest.class).getItem(model.getId())).withSelfRel());
        return model;
    }



//    @PutMapping("/items/{id}")
//    public ItemWrapper updateItem(@Valid @RequestBody ItemWrapper itemWrapper,@Min(1) @PathVariable("id") int id){
//        itemWrapper.setId(id);
//        ItemWrapper item = itemService.updateItem(itemWrapper);
//        inventoryProxy.updateStockItem(item,id);
//        String uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost:8765").path("/items").buildAndExpand(item.getId()).toUriString();
//        item.setUri(uri);
//        return item;
//
//    }
    @PutMapping("/items/{id}")
    public ItemModel updateItem(@Valid @RequestBody ItemWrapper itemWrapper, @Min(1) @PathVariable("id") int id){
        itemWrapper.setId(id);
        itemWrapper = itemService.updateItem(itemWrapper);
        if (!inventoryProxy.updateStockItem(itemWrapper,id)){
            throw new RuntimeException("Not enough stock.");
        }
        ItemModel model = itemAssembler.toModel(itemWrapper);
        model.add(WebMvcLinkBuilder.linkTo(CatalogRest.class).slash(model.getId()).withSelfRel());
        return model;
    }

    @PutMapping(value = "/items/{id}", produces = "application/vnd.app-v2+json")
    public ItemModel updateItemVersion2(@Valid @RequestBody ItemWrapper itemWrapper, @Min(1) @PathVariable("id")int id){
        itemWrapper.setId(id);
        ItemModel model = itemAssembler.toModel(itemService.updateItem(itemWrapper));
        inventoryProxy.updateStockItem(itemWrapper,id);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CatalogRest.class).getItem(id)).withSelfRel());
        return model;
    }

    @DeleteMapping("/items/{id}")
    public HttpStatus deleteItemFromCatalog(@Min(1)@PathVariable("id") int id){
            itemService.deleteItemFromCatalog(id);
            if (inventoryProxy.removeStock(id)){
                return HttpStatus.ACCEPTED;
            }
            return HttpStatus.BAD_REQUEST;
        }

    @GetMapping("items/productType/{id}")
    public List<ItemWrapper> getItemsByProductType(@Min(1) @PathVariable("id") String id){
        return itemService.getItemsByProductType(id);
    };


    @GetMapping("items/brand/{id}")
    public List<ItemWrapper> getItemsByBrand(@Min(1) @PathVariable("id") String id){
    return itemService.getItemsByBrand(id);
    };
}

