package com.io.Ogani.repository;

import com.io.Ogani.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email =?1")
    public Customer findByEmail(String email);


    @Query("SELECT c FROM Customer c WHERE c.verificationCode =?1")
    public Customer findByVerificationCode(String code);


    @Query("UPDATE Customer c SET c.enabled = true WHERE c.id =?1")
    @Modifying
    public void enable(Integer id);

    Iterable<Customer> findAll(Sort sort);
}
