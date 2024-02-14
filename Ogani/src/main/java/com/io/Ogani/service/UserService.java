package com.io.Ogani.service;

import com.io.Ogani.model.Role;
import com.io.Ogani.model.User;
import com.io.Ogani.repository.RoleRepository;
import com.io.Ogani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

@Autowired
private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAllUsers(){
        return (List<User>) userRepo.findAll();
    }

    public List<Role> listAllRoles(){
        return (List<Role>) roleRepo.findAll();
    }

    public void save(User user) {
        encodePassword(user);
        userRepo.save(user);
    }

    private void encodePassword(User user){
        String encodedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(String email){

        User userByEmail = userRepo.getUserByEmail(email);
        return userByEmail == null;
    }
}
