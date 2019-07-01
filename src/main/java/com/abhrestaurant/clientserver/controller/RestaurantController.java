package com.abhrestaurant.clientserver.controller;


import com.abhrestaurant.clientserver.exception.ResourceNotFoundException;
import com.abhrestaurant.clientserver.model.Restaurant;
import com.abhrestaurant.clientserver.model.Reviews;
import com.abhrestaurant.clientserver.repository.RestaurantRepository;
import com.abhrestaurant.clientserver.repository.ReviewsRepository;
import com.abhrestaurant.clientserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allrestaurant")
    public List<Restaurant> getAllRestaurant(){

        System.out.println("Should be getted all restaurant");
        return restaurantRepository.findAll();
    }



    @GetMapping("/restaurant/matchpatern/{patern}/{mark}")
    public List<Restaurant> getMatchedRestaurants(@PathVariable String patern, @PathVariable Float mark){

        System.out.println("Should be getted all restaurant");
        return restaurantRepository.getMatchedRestaurants(patern,mark);
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


    @GetMapping("/restaurant/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id){

        System.out.println("Should be getted restaurant " + id);
        return restaurantRepository.findRestaurantById(id);
    }

    @PostMapping("/save/restaurant")
    public String saveRestaurant(@RequestBody Restaurant restaurant){

        restaurantRepository.save(restaurant);
        System.out.println("Should be saved restaurant");
        return ("Restaurant " + restaurant.getName() + "succesfully saved");
    }

    @PutMapping("/update/restaurant")
    public boolean updateRestaurant(@RequestBody Restaurant restaurant){


        restaurantRepository.save(restaurant);
        System.out.println("Should be saved restaurant");
        return true;
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurantById(@PathVariable Long id){

        System.out.println("Should be deleted restaurant");
    }
/*
    @PostMapping("/save/review/{email}")
    public String saveReview(@RequestBody Reviews reviews,@PathVariable String email){

        System.out.println("Should be saved review" + reviews.getComment() );
        return userRepository.findUserByEmail(email).map(user -> {
                    reviews.setUser(user);
                    reviewsRepository.save(reviews);
                    return "s";
                }).orElseThrow(() -> new ResourceNotFoundException("User " + email + " not found"));


       // return ("Review from user " + reviews.getUser().getId() + "succesfully saved");
    }

*/
}
