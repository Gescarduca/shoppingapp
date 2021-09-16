package com.malsum.shoppingsessionservice.dao;

import com.malsum.shoppingsessionservice.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends CrudRepository<Item,Integer> {

    @Query("select cost from Item where id=:itemId")
    double getCost(@Param("itemId") int itemId);
}
