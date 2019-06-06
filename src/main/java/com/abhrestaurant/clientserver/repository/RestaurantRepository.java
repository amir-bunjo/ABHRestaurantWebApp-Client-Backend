package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {


}
