package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByFirstname(String firstname);

    User findUserByEmail(String email);


}
