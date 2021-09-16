package com.malsum.catalogservice.service;

import com.malsum.catalogservice.entity.Brand;
import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.entity.ItemWrapper;
import com.malsum.catalogservice.entity.ProductType;

import java.util.List;

public interface ItemService {
    public ItemWrapper getItem(int id);
    public List<ItemWrapper> getItemsByProductType(String idType);
    //public List<Item> getItemsByProductType(String idType);
    public List<ItemWrapper> getItemsByBrand(String idBrand);
    public ItemWrapper saveItem(ItemWrapper itemWrapper);
    public void deleteItemFromCatalog(int id);
    public Brand saveBrand(Brand brand);
    public ProductType saveProductType(ProductType productType);
    public Brand findBrand(String id);
    public ProductType findProductType(String id);
    public ItemWrapper updateItem(ItemWrapper itemWrapper);
    public List<ItemWrapper> getItems();
}
