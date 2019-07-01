package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByFirstname(String firstname);

    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT u.id FROM users u WHERE u.email = ?1 ", nativeQuery = true )
    Long getIdByEmail(String email);



}
