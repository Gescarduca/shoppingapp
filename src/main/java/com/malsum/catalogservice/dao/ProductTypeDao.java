package com.malsum.catalogservice.dao;

import com.malsum.catalogservice.entity.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface ProductTypeDao extends CrudRepository<ProductType,String> {
}
