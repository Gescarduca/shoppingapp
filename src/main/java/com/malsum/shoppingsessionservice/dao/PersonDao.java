package com.malsum.shoppingsessionservice.dao;

import com.malsum.shoppingsessionservice.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person,Integer> {
}
