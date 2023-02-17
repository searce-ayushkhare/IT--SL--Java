package com.example.customer.repositories;

import com.example.customer.entities.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Modifying
    @Query("UPDATE Customer c SET c.email =:newEmail WHERE c.id =:custId")
    void updateEmailById(@Param("custId") int id, @Param("newEmail") String email);
}
