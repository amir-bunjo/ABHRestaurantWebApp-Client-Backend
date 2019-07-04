package com.abhrestaurant.clientserver.controller;

import com.abhrestaurant.clientserver.exception.ResourceNotFoundException;
import com.abhrestaurant.clientserver.model.Restaurant;
import com.abhrestaurant.clientserver.model.Reviews;
import com.abhrestaurant.clientserver.repository.ReviewsRepository;
import com.abhrestaurant.clientserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ReviewsController {

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/save/review/{email}")
    public String saveReview(@RequestBody Reviews reviews, @PathVariable String email){

        System.out.println("Should be saved review" + reviews.getComment() );
        return userRepository.findUserByEmail(email).map(user -> {
            reviews.setUser(user);
            reviewsRepository.save(reviews);
            return "s";
        }).orElseThrow(() -> new ResourceNotFoundException("User " + email + " not found"));


        // return ("Review from user " + reviews.getUser().getId() + "succesfully saved");
    }

    @GetMapping("/reviews/{restaurantId}/{username}")
    public Reviews getRestaurantById(@PathVariable Long restaurantId, @PathVariable String username){

        Long userId = userRepository.getIdByEmail(username);
        System.out.println("Should be getted review with restaurantId  " + restaurantId);
        return reviewsRepository.findReview(restaurantId,userId);
    }

    @GetMapping("reviews/{restaurantId}")
    public Reviews getByRestaurantId(@PathVariable Long restaurantId){

        return reviewsRepository.findByRestaurantId(restaurantId);
    }

}
