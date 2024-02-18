package com.io.Ogani.controller;

import com.io.Ogani.model.Country;
import com.io.Ogani.model.Customer;
import com.io.Ogani.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {


    @Autowired
    private CustomerService service;

    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        List<Country> listCountries = service.listAllCountries();
        model.addAttribute(null, listCountries);
        model.addAttribute("PageTitle", "Customer Registration");
        model.addAttribute("customer", new Customer());

        return "register_form";

    }

}
