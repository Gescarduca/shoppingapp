package com.malsum.catalogservice.dao;

import com.malsum.catalogservice.entity.Brand;
import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.entity.ProductType;

import java.util.List;

public interface ItemDao {

    //client request an item from catalog for more information
    //client request a list of available items from a specific category(consumables or clothes like that)
    //shopping service requests to remove product from catalog due to shortage of stock after client bought the items
    //add new products to catalog(should only be available to employees)
    //get list of items from specific brand
    public Item getItem(int id);
    public ProductType getItemsByProductType(String type);
//    public List<Item> getItemsByProductType(String type);
    public Brand getItemsByBrand(String brand);
    public Item saveItem(Item item);
    public Brand saveBrand(Brand brand);
    public ProductType saveProductType(ProductType productType);
    public void deleteItemFromCatalog(int id);
    public Brand getBrand(String id);
    public ProductType getProductType(String id);
    public List<Item> getItems();
    public Boolean exists(Object o);
    //public Item updateItem(int id);

}
