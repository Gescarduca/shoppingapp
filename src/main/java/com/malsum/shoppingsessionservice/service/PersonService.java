package com.malsum.shoppingsessionservice.service;

import com.malsum.shoppingsessionservice.dao.PersonDao;
import com.malsum.shoppingsessionservice.entity.Person;
import com.malsum.shoppingsessionservice.exceptions.NotFoundException;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonDao personDao;

    public Person findById(int id){
        Optional<Person> person = personDao.findById(id);
        if (person.isEmpty()){
            throw new NotFoundException("Person Id not found");
        }
        return person.get();
    }
}
