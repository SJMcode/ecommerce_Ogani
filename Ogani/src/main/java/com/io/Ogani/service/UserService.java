package com.io.Ogani.service;

import com.io.Ogani.model.Role;
import com.io.Ogani.model.User;
import com.io.Ogani.repository.RoleRepository;
import com.io.Ogani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        boolean isUpdatingUser = (user.getId()!=null);
        if(isUpdatingUser){
            User existingUser = userRepo.findById(user.getId()).get();

            if(user.getPassword().isEmpty()){
                user.setPassword(existingUser.getPassword());
            }
            else {
                encodePassword(user);
            }

        }
        else{

        encodePassword(user);
        }
        userRepo.save(user);
    }

    private void encodePassword(User user){
        String encodedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email){

        User userByEmail = userRepo.getUserByEmail(email);
        if(userByEmail == null) return true;
        boolean isCreatingNew = (id == null);
        if(isCreatingNew){
            if(userByEmail !=null) return false;
        }
        else{
            if(userByEmail.getId()!= id){return  false;}
        }

        return true;
    }

    public List<Role> listRoles() {

        return (List<Role>) roleRepo.findAll();
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return userRepo.findById(id).get();
         }catch (NoSuchElementException e){
                throw new UserNotFoundException("User not found with id "+id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepo.countById(id);
          if(countById ==null || countById==0){
              throw new UserNotFoundException("Could not find any user with ID" + id);
          }
          userRepo.deleteById(id);


    }
}
