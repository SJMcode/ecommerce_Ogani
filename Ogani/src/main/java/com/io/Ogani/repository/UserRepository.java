package com.io.Ogani.repository;

import com.io.Ogani.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email =:email")
    public User getUserByEmail(@Param("email") String email);


    public Long countById(Integer id);
}
