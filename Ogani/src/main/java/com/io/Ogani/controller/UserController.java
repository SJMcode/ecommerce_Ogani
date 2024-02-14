package com.io.Ogani.controller;

import com.io.Ogani.model.Role;
import com.io.Ogani.model.User;
import com.io.Ogani.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/oganiAdmin")
    public String home(){
    return "index";
}

    @GetMapping("/users")
    public String listAll(Model model){
        List<User> listUsers = service.listAllUsers();
        List<Role> listRoles = service.listAllRoles();

        model.addAttribute("listUsers", listUsers);
        model.addAttribute("listRoles", listRoles);

        //System.out.println(listUsers);
        System.out.println(listRoles);
        return "users";
    }

        @GetMapping("/users/new")
        public String newUser(Model model){
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user", user);
        return "user_form";
        }

        @PostMapping("/users/save")
        public String saveUser(User user, RedirectAttributes redirectAttributes){
            //System.out.println(user);
            service.save(user);
            redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
            return "redirect:/users";
        }
}
