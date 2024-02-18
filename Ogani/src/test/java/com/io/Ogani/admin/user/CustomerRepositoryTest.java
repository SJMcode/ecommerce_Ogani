package com.io.Ogani.admin.user;

import com.io.Ogani.model.Customer;
import com.io.Ogani.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest{

    @Autowired private CustomerRepository repo;

    @Test
    public void testListAll() {
        Iterable<Customer> listCustomers = repo.findAll();
        listCustomers.forEach(c -> {
            System.out.printf("%-30s - %-20s %-20s %-20s\n", c.getFullName(),
                    c.getCountry().getName(), c.getState(), c.getCity());
        });
    }

    @Test
    public void testSortByMultipleColumns() {
        Sort sort = Sort.by("country_name").ascending();
        sort = sort.and(Sort.by("state").ascending());
        sort = sort.and(Sort.by("city").ascending());

        Iterable<Customer> listCustomers = repo.findAll(sort);

        listCustomers.forEach(c -> {
            System.out.printf("%-30s - %-20s %-20s %-20s\n", c.getFullName(),
                    c.getCountry().getName(), c.getState(), c.getCity());
        });
    }
}