package com.io.Ogani.service;

import com.io.Ogani.model.User;
import com.io.Ogani.repository.UserRepository;
import com.io.Ogani.security.OganiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OganiUserDetailsService implements UserDetailsService {


   @Autowired
   private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(email);
        if(user !=null){
            return new OganiUserDetails(user);
        }
        throw new UsernameNotFoundException("Could not find user with email"+ email);
    }
}
