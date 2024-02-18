package com.io.Ogani.admin.user;
import static org.assertj.core.api.Assertions.*;
import com.io.Ogani.model.Role;
import com.io.Ogani.model.User;
import com.io.Ogani.repository.RoleRepository;
import com.io.Ogani.repository.UserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {




        @Autowired
        private UserRepository repo;

        @Autowired
        private EntityManager entityManager;

        @Test
        public void testCeateUserWithOneROle(){
            Role roleAdmin = entityManager.find(Role.class, 1);
                User userSaf = new User("safir@gmail.com","saf123", "safir", "manghat");
                userSaf.addRole(roleAdmin);
                User savedUser = repo.save(userSaf);
                assertThat(savedUser.getId()).isGreaterThan(0);
        }

    @Test
    public void testCeateUserWithTwoROle(){
        User userRavi = new User("ravi@gmail.com","ravi2020", "ravi", "kumar");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);
        userRavi.addRole(roleEditor);
        userRavi.addRole(roleAssistant);

        User savedUser = repo.save(userRavi);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){

        Iterable<User> listUsers = repo.findAll();
            listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void getUserById(){

        User userSaf = repo.findById(1).get();
        System.out.println(userSaf);
        assertThat(userSaf).isNotNull();

    }
    @Test
    public void testUpdateUserDetails(){
        User userSaf = repo.findById(1).get();
        userSaf.setEnabled(true);
        userSaf.setEmail("safjavaprogramming@gmail.com");
        repo.save(userSaf);

    }
    @Test
    public void testUpdateUserRoles(){
        User userRavi = repo.findById(3).get();

        Role roleSalesPerson = new Role(8);
        Role roleEditor = new Role(9);


        userRavi.getRoles().remove(roleEditor);
        userRavi.addRole(roleSalesPerson);
        repo.save(userRavi);

    }

    @Test
    public void testDeleteUser(){
            Integer userId = 3;
            repo.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail(){
        String email = "ola@gmail.com";
        User user = repo.getUserByEmail(email);
        assertThat(user).isNotNull();

    }

    @Test
    public void testCountById(){

            Integer id =1;
            Long countById = repo.countById(id);

            assertThat(countById).isNotNull().isGreaterThan(0);

    }


}
