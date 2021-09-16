package com.malsum.catalogservice.service;

import com.malsum.catalogservice.assemblers.ItemAssembler;
import com.malsum.catalogservice.dao.BrandDao;
import com.malsum.catalogservice.dao.ItemDao;
import com.malsum.catalogservice.dao.ProductTypeDao;
import com.malsum.catalogservice.entity.Brand;
import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.entity.ItemWrapper;
import com.malsum.catalogservice.entity.ProductType;
import com.malsum.catalogservice.exceptions.NotFoundException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemDao itemDao;

    @Autowired
    BrandDao brandDao;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ItemAssembler itemAssembler;

    @Autowired
    ProductTypeDao productTypeDao;

    @Override
    @Transactional
    public ItemWrapper getItem(int id) {
        return itemAssembler.toWrapper(itemDao.getItem(id));
    }

    @Override
    @Transactional
    public List<ItemWrapper> getItemsByProductType(String productId) {

//        return itemDao.getItemsByProductType(productId).getItems().stream().map(item -> changeToItemWrapper(item))
//                .sorted(((item, t1) -> item.getName().compareTo(t1.getName())))
//                .collect(Collectors.toList());
        return itemDao.getItemsByProductType(productId).getItems().stream().map(item -> itemAssembler.toWrapper(item))
                .sorted(((item, t1) -> item.getName().compareTo(t1.getName())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemWrapper> getItemsByBrand(String brandId) {

        if (!brandDao.existsById(brandId)){
            throw new NotFoundException("Brand does not exist");
        }

        return itemDao.getItemsByBrand(brandId).getItemsProduced().stream().map(item -> itemAssembler.toWrapper(item))
                .sorted((item, t1) -> item.getName().compareTo(t1.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItemWrapper saveItem(ItemWrapper itemWrapper) {
        //query Database for existing brand and productType
        Brand brand;
        brand = beanFactory.getBean(Brand.class);
        brand.setId(itemWrapper.getBrand());

        ProductType productType;
        productType = beanFactory.getBean(ProductType.class);
        productType.setId(itemWrapper.getType());
        //if brand and productType do not exist create
        if (!(itemDao.exists(brand))){//problem with this method felt dumb to create a whole implementation just for one very specific method
            System.out.println("in brand");
            saveBrand(brand);
        }
        if (!(itemDao.exists(productType))){
            System.out.println(productType + " in the if statement");
            saveProductType(productType);
        }
        itemDao.getBrand(itemWrapper.getBrand());
        itemDao.getProductType(itemWrapper.getType());
        return changeToItemWrapper(itemDao.saveItem(changeToItem(itemWrapper)));
    }

    @Override
    @Transactional
    public void deleteItemFromCatalog(int id) {
        itemDao.deleteItemFromCatalog(id);
    }

    @Override
    @Transactional
    public Brand saveBrand(Brand brand) {
        return itemDao.saveBrand(brand);
    }

    @Override
    @Transactional
    public ProductType saveProductType(ProductType productType) {
        return itemDao.saveProductType(productType);
    }


    @Override
    @Transactional
    public Brand findBrand(String id) {
        if (!brandDao.existsById(id)){
            throw new NotFoundException("Brand not found");
        }
        return itemDao.getBrand(id);
    }

    @Override
    @Transactional
    public ProductType findProductType(String id) {
        if (!productTypeDao.existsById(id)){
            throw new NotFoundException("Product Type not found");
        }
        return itemDao.getProductType(id);
    }



    @Override
    public ItemWrapper updateItem(ItemWrapper itemWrapper) {
       return changeToItemWrapper(itemDao.saveItem(changeToItem(itemWrapper)));
    }

    @Override
    public List<ItemWrapper> getItems(){
        return itemDao.getItems().stream().map(item -> itemAssembler.toWrapper(item))
                .sorted((itemWrapper, t1) -> itemWrapper.getName().compareTo(t1.getName()))
                .collect(Collectors.toList());
    }
//problem with this method is that if use wants to change brand or product type
    //he will be forced to create a new item and delete the old version since get reference throws exception instead of null
    //not allowing me to create a new brand or type in case the passed paramenters are not found in DB
    private Item changeToItem(ItemWrapper itemWrapper){
        Item item = beanFactory.getBean(Item.class);
        item.setId(itemWrapper.getId());
        item.setDescription(itemWrapper.getDescription());
   //     item.setStock(itemWrapper.getStock());
        item.setBrandId(itemDao.getBrand(itemWrapper.getBrand()));
        item.setProductType(itemDao.getProductType(itemWrapper.getType()));
        item.setName(itemWrapper.getName());
        item.setCost(itemWrapper.getCost());
        return item;
    }
    private ItemWrapper changeToItemWrapper(Item item){
        ItemWrapper itemWrapper = beanFactory.getBean(ItemWrapper.class);
        itemWrapper.setId(item.getId());
        itemWrapper.setDescription(item.getDescription());
   //     itemWrapper.setStock(item.getStock());
        itemWrapper.setBrand(item.getBrandId().getId());
        itemWrapper.setType(item.getProductType().getId());
        itemWrapper.setName(item.getName());
        itemWrapper.setCost(item.getCost());
        return itemWrapper;
    }

//    private Collection<?> sort(Collection<?> collection, Predicate<Item> condition){
//
//    }
}
