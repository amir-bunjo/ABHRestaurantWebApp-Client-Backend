package com.abhrestaurant.clientserver.controller;


import com.abhrestaurant.clientserver.exception.ResourceNotFoundException;
import com.abhrestaurant.clientserver.model.Reservation;
import com.abhrestaurant.clientserver.model.Restaurant;
import com.abhrestaurant.clientserver.model.Reviews;
import com.abhrestaurant.clientserver.model.Table;
import com.abhrestaurant.clientserver.repository.RestaurantRepository;
import com.abhrestaurant.clientserver.repository.ReviewsRepository;
import com.abhrestaurant.clientserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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



    @PostMapping("/restaurant/matchpatern/{patern}/{mark}/{price}/{page}")
    public List<Restaurant> getMatchedRestaurants(@PathVariable String patern, @PathVariable Float mark, @PathVariable Float price, @PathVariable int page , @RequestBody List<String> cousines) {

        List<Restaurant> matchedRestaurants = null;
        List<Restaurant> found = new ArrayList<Restaurant>();

        if (patern.equals("-"))
            matchedRestaurants = restaurantRepository.getMatchedRestaurantsByMarkAndPrice(mark, price);
        else
            matchedRestaurants = restaurantRepository.getMatchedRestaurants(patern, mark, price);

        System.out.println("Should be getted all restaurant" + patern);

        if (cousines.size() == 0)
            System.out.println("empty cousines");

        for (Restaurant matchedRestaurant : matchedRestaurants) {
            for (String cousine : cousines) {
                if (matchedRestaurant.getFoodTypes().contains(cousine)) {
                    found.add(matchedRestaurant);
                    break;
                }
            }
        }

        for (String cousine : cousines)
            System.out.println(cousine);

        matchedRestaurants.removeAll(found);

        if (cousines.size() != 0)
            matchedRestaurants = found;


        int size = matchedRestaurants.size();


      return matchedRestaurants.subList(page, size-page<9 ? size : page+9);
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

    @PostMapping("/restaurant/count/matchpatern/{patern}/{mark}/{price}/{page}")
    public int getCountOfMatchedRestaurants(@PathVariable String patern, @PathVariable Float mark, @PathVariable Float price, @PathVariable int page , @RequestBody List<String> cousines) {

        List<Restaurant> matchedRestaurants = null;
        List<Restaurant> found = new ArrayList<Restaurant>();

        if (patern.equals("-"))
            matchedRestaurants = restaurantRepository.getMatchedRestaurantsByMarkAndPrice(mark, price);
        else
            matchedRestaurants = restaurantRepository.getMatchedRestaurants(patern, mark, price);

        System.out.println("Should be getted all restaurant" + patern);

        if (cousines.size() == 0)
            System.out.println("empty cousines");

        for (Restaurant matchedRestaurant : matchedRestaurants) {
            for (String cousine : cousines) {
                if (matchedRestaurant.getFoodTypes().contains(cousine)) {
                    found.add(matchedRestaurant);
                    break;
                }
            }
        }

        for (String cousine : cousines)
            System.out.println(cousine);

        matchedRestaurants.removeAll(found);

        if (cousines.size() != 0)
            matchedRestaurants = found;


        int size = matchedRestaurants.size();

        return size;

    }

    @GetMapping("/restaurant/avalable/{name}/{seats}/{dateString}/{timeString}")
    public List<Restaurant> getAvailableRestaurant( @PathVariable Integer seats , @PathVariable String dateString, @PathVariable String timeString, @PathVariable String name) {

        System.out.print(name + seats + dateString + timeString);

        Time selectedTime = Time.valueOf(timeString);
        Time startTime = Time.valueOf(timeString);
        Time endTime = Time.valueOf(timeString);

        startTime.setHours(selectedTime.getHours() -1);
        endTime.setHours(selectedTime.getHours() + 1);

        Date selectedDate = Date.valueOf(dateString);

        List<Restaurant> found = new ArrayList<Restaurant>();
        //List<Restaurant> restaurants = new ArrayList<Restaurant>();
        List<Restaurant>  restaurants ;
        if(name.equals("-") || name.equals(null)) {
            System.out.print("should be getted all");


            restaurants = restaurantRepository.findAll();
        }
        else {
            System.out.print( "should be getted res with name: " + name );


            restaurants = restaurantRepository.getAllRestaurantsWithMatchedName(name);
        }


        int pom=0;

        for(Restaurant restaurant: restaurants){
            for(Table table: restaurant.getTables()){
                System.out.println("Restaurant:" + restaurant.getName()  );
                if(table.getSeats().equals(seats)) {
                    System.out.println("Restaurant:" + restaurant.getName() + "has a selected seats" + table.getReservations().size()  );
                    if(table.getReservations().size()==0 ) {
                        if(!found.contains(restaurant))
                            found.add(restaurant);

                        System.out.println("nema rezervacija u " + restaurant.getName());
                        break;
                    }
                    pom=0;
                    for (Reservation reservation : table.getReservations()) {
                        if (reservation.getDate().equals(selectedDate) && reservation.getTime().before(endTime) && reservation.getTime().after(startTime)) {
                           pom++;
                            System.out.println("Restaurant: " + restaurant.getName() + " has a reservation at" + "rezervacija prije " + reservation.getTime().before(endTime) + "rezervacija poslije " + reservation.getTime().after(startTime) +  reservation.getDate() + " " + reservation.getTime());
                        }
                        else
                            System.out.println("False Restaurant: " + restaurant.getName() + " has a reservation at" + "rezervacija prije " + reservation.getTime().before(endTime) + "rezervacija poslije " + reservation.getTime().after(startTime) +  reservation.getDate() + " " + reservation.getTime());

                    }
                    if(pom==0 && !found.contains(restaurant))
                        found.add(restaurant);
                }
            }
        }

        restaurants.removeAll(found);



        return found;
    }

}
