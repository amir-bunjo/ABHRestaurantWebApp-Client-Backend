package com.abhrestaurant.clientserver.controller;

import com.abhrestaurant.clientserver.model.Meal;
import com.abhrestaurant.clientserver.model.User;
import com.abhrestaurant.clientserver.repository.MealsRepository;
import com.abhrestaurant.clientserver.repository.RestaurantRepository;
import com.abhrestaurant.clientserver.repository.ReviewsRepository;
import com.abhrestaurant.clientserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MealsController {

    @Autowired
    MealsRepository mealsRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/allmeals")
    public List<Meal> getAllMeals(){

        System.out.println("Should be getted all users");
        return mealsRepository.findAll();
    }

    @PostMapping("/save/meals")
    public boolean getAllMeals(@RequestBody List<Meal> lisOfMeal){

        System.out.println("Should be getted all users");

        for(Meal meal: lisOfMeal)
            mealsRepository.save(meal);

        return true;
    }

    @PostMapping("/save/meals/{restaurantId}")
    public boolean saveMealsByRestaurantId(@RequestBody List<Meal> lisOfMeal, @PathVariable Long restaurantId){

        System.out.println("Should be getted all users");
        for(Meal meal: lisOfMeal) {
            meal.setRestaurant(restaurantRepository.getOne(restaurantId));
            mealsRepository.save(meal);
        }

        return true;
    }



}