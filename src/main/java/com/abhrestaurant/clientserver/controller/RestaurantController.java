package com.abhrestaurant.clientserver.controller;


import com.abhrestaurant.clientserver.model.Restaurant;
import com.abhrestaurant.clientserver.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurant(){

        System.out.println("Should be getted all restaurant");
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurant/matchpatern/{patern}")
    public List<Restaurant> getMatchedRestaurants(@PathVariable String patern){

        System.out.println("Should be getted all restaurant");
        return restaurantRepository.getMatchedRestaurants(patern);
    }

    @GetMapping("/restaurant/length")
    public Long getRestaurantsTableLength(){

        System.out.println("Should be getted restaurants table length");
        return restaurantRepository.getTableLength();
    }

    @GetMapping("/restaurants/{startindex}")
    public List<Restaurant> getRestaurantsWithStartIndex(@PathVariable Long startindex ){

        System.out.println("Should be getted 9 restaurants starts at: " + startindex) ;
        return restaurantRepository.findRestaurantsWithStartIndex(startindex);
    }


    @GetMapping("/restaurant/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable Long id){

        Restaurant restaurant = restaurantRepository.getOne(id);
        System.out.println("Should be getted restaurant " + id);
        return restaurantRepository.getOne(id);
    }

    @PostMapping("/restaurant")
    public void saveRestaurant(@RequestBody Restaurant restaurant){

        restaurantRepository.save(restaurant);
        System.out.println("Should be saved restaurant");
    }

    @PutMapping("/restaurant")
    public void updateRestaurant(@RequestBody Restaurant restaurant){


        restaurantRepository.save(restaurant);
        System.out.println("Should be saved restaurant");
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurantById(@PathVariable Long id){

        System.out.println("Should be deleted restaurant");
    }
}
