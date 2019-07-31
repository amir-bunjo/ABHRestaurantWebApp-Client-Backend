package com.abhrestaurant.clientserver.controller;

import com.abhrestaurant.clientserver.model.Login;
import com.abhrestaurant.clientserver.model.User;
import com.abhrestaurant.clientserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/allusers")
    public List<User> getAllUsersDescending(){

        System.out.println("Should be getted all users");
        return userRepository.getAllUsers();
    }

    @GetMapping("get/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id){

        User user = userRepository.getOne(id);
        System.out.println("Should be getted user " + id);
        return userRepository.findById(id);
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

    @GetMapping("/user/byemail/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        System.out.println("Should be getted user: " + username);
        return userRepository.findUserByEmail(username);
    }

    @GetMapping("/user_id/byemail/{username}")
    public Long getIdByUsername(@PathVariable String username) {


        return userRepository.getIdByEmail(username);
    }

    @GetMapping("get/allemails")
    public List<String> getAllUsersEmails(){

        List<User> users = userRepository.getAllUsers();
        List<String> usersEmails = new ArrayList<String>();

        for(User user: users)
            usersEmails.add(user.getEmail());

        return usersEmails;

    }

}

