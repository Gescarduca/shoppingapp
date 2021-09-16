package com.malsum.shoppingsessionservice.dao;

import com.malsum.shoppingsessionservice.entity.Item;
import com.malsum.shoppingsessionservice.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends CrudRepository<Transaction,Integer> {

    @Query("from Transaction where person_id= :id and date between :startDate and :endDate")
    public List<Transaction> getTransactionRecords(@Param("startDate") String startDate,@Param("endDate") String endDate, @Param("id") int personId);

//    @Query("from Transaction where person_id= :id")
//    public List<Transaction> getTransactionRecords(@Param("id") int personId);

    @Query("from Transaction")
    public List<Transaction> transactions();

}
