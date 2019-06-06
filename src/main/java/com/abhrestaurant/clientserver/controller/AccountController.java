package com.abhrestaurant.clientserver.controller;

import com.abhrestaurant.clientserver.model.Login;
import com.abhrestaurant.clientserver.model.User;
import com.abhrestaurant.clientserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    UserRepository userRepository;


    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogins" })
    public Login validateLogins() {
        return new Login("User successfully authenticated");
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){

        System.out.println("Should be getted all users");
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@RequestBody Long id){

        User user = userRepository.getOne(id);
        System.out.println("Should be getted user " + id);
        return userRepository.getOne(id);
    }

    @PostMapping ("create/user")
    public String saveUser(@RequestBody User user){

       // userRepository.save(user);
        System.out.println("Should be saved");
        System.out.println("Succesfully registered user: " + user.getFirstname());
        userRepository.save(user);
        return "successfully";
    }

    @PutMapping ("/user")
    public void updateUser(@RequestBody User user){


        userRepository.save(user);
        System.out.println("Should be saved");
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable Long id){


        userRepository.deleteById(id);

        System.out.println("Succesfully deleted user " + id);

        return ("Succesfully  deleted user " + id);
    }

}

