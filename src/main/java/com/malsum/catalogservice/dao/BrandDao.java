package com.malsum.catalogservice.dao;

import com.malsum.catalogservice.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends CrudRepository<Brand, String> {
}
