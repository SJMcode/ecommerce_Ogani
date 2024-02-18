package com.io.Ogani.service;

import com.io.Ogani.model.Country;
import com.io.Ogani.model.Customer;
import com.io.Ogani.model.User;
import com.io.Ogani.repository.CountryRepository;
import com.io.Ogani.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired private CountryRepository countryRepo;
    @Autowired private CustomerRepository customerRepo;


    public List<Country> listAllCountries(){

        return countryRepo.findAllByOrderByNameAsc();
    }

    public boolean isEmailUnique(Integer id, String email){

        Customer userByEmail = customerRepo.findByEmail(email);
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

}
