package com.io.Ogani.admin.user;

import com.io.Ogani.model.Role;
import com.io.Ogani.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCteateFirstRole(){
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCteateRestRole(){
        Role roleSalesPerson = new Role("salesPerson", "manage product, "
                +"customers, report");
        Role roleEditor = new Role("Edtitor", "manage catagories, brands, "
                +"products, articles, and menus");
        Role roleShipper = new Role("Shipper", "view products, view orders, "
                +"and update order status");
        Role roleAssistant = new Role("Assistant", "manage questions and reviews");
        repo.saveAll(List.of(roleSalesPerson, roleEditor, roleShipper, roleAssistant));

    }

}
