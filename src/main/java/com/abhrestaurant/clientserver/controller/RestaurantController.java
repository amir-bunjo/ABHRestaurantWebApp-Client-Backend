package com.abhrestaurant.clientserver.controller;


import com.abhrestaurant.clientserver.exception.ResourceNotFoundException;
import com.abhrestaurant.clientserver.model.Reservation;
import com.abhrestaurant.clientserver.model.Restaurant;
import com.abhrestaurant.clientserver.model.Reviews;
import com.abhrestaurant.clientserver.model.Table;
import com.abhrestaurant.clientserver.repository.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.cloudinary.Cloudinary;



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

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TableRepository tableRepository;

    @PersistenceContext
    private EntityManager em;


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
    //ADMIN
    @GetMapping("/restaurant/name/id/{startIndex}")
    public List<Object> getAllRestaurantsNameAndId(@PathVariable Long startIndex){

        System.out.println("Should be getted restaurant's id and name " );
        return restaurantRepository.getAllRestaurantsNameAndId(startIndex);
    }

    @PostMapping("/save/restaurant/{coverName}/{logoName}")
    public String saveRestaurant(@RequestBody Restaurant restaurant, @PathVariable String coverName, @PathVariable String logoName) throws Exception{
        if(restaurant.getCoverphoto().startsWith("data:image"))
         restaurant.setCoverphoto(this.uploadRestaurantImageToCloudinary(coverName,restaurant.getCoverphoto()));
        if(restaurant.getPromophoto().startsWith("data:image"))
            restaurant.setPromophoto(this.uploadRestaurantImageToCloudinary(logoName,restaurant.getPromophoto()));
        System.out.println(restaurant.getId());
        if(restaurant.getId()!=null) {
            restaurantRepository.findById(restaurant.getId()).map(res -> {
                restaurant.setTables(res.getTables());
                restaurantRepository.save(restaurant);
                System.out.println("Should be saved restaurant");
                return ("Restaurant " + restaurant.getName() + "succesfully saved");
            });
        }
        else
            restaurantRepository.save(restaurant);


        return ("Restaurant " + restaurant.getName() + "succesfully saved");

    }

    @PutMapping("/update/restaurant")
    public boolean updateRestaurant(@RequestBody Restaurant restaurant){

        restaurantRepository.save(restaurant);
        System.out.println("Should be saved restaurant");
        return true;
    }

    @Transactional
    @DeleteMapping("/restaurant/{restaurantId}")
    public int deleteRestaurantById(@PathVariable Long restaurantId){

        System.out.println("Should be deleted restaurant");


       // restaurantRepository.deleteById(restaurantId);
        //return true;
       // reservationRepository.deleteByRestaurantId(restaurantId);

        restaurantRepository.findById(restaurantId).map(restaurant -> {

          //  Query query = em.createNativeQuery(
          //          "DELETE FROM reservation  WHERE restaurant_id = :p");
         //   int deletedCount = query.setParameter("p", restaurantId).executeUpdate();
            int countReviewas = reviewsRepository.deleteReviewsByRestaurant(restaurantId);
             int count = reservationRepository.deleteByRestaurant(restaurantId);

            System.out.println("count of delted reservations is: " + count);

          int countOftab = tableRepository.deleteTableByRestaurant(restaurantId);

            System.out.println("count of delted tables is: " + countOftab);


           restaurantRepository.deleteById(restaurant.getId());
            return count;
        });
        return 0;
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

    @PostMapping("/restaurant/image/upload/{imgName}")
    public String uploadRestaurantImageToCloudinary(@PathVariable String imgName, @RequestBody() String imgUrl) throws Exception{
        //obrisat sve ako treba jer bugaStrign
        String cloudName = "dacid3ish";
        String apiKey = "395382115388869";
        String apiSecret = "kEPDIlIhu1_sPsS2mzjfwce-7ZY";

        //String[] imgArray = imageModel.getUrlsInDatabase();
        //  String newImage = imageModel.getImgUrl();
        // Integer imgToEdit = imageModel.getImageToEdit();  // old way

        String arrayToUpload = "";


        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "kkraljevic",
                "api_key", "555473889443414",
                "api_secret", "46lG4apQ5T08ciyBTbn21a7MxBo"));

        Map response = cloudinary.uploader().upload(imgUrl,
                ObjectUtils.asMap("public_id", "abh/restaurants/" + imgName));


        String url = (String) response.get("secure_url");

        System.out.println("secure url: " + response.get("secure_url"));

        // System.out.println("ispod ispis  "+ imgToEdit + "  imgarraylength  " + imgArray.length);


/*
        for(int i=0;i<imgArray.length;i++) {

            if(i!= imgToEdit && imgToEdit>=0)
                arrayToUpload +=  i!=imgArray.length - 1 ? imgArray[i] + "," : imgArray[i];
            else if(i== imgToEdit && imgToEdit>=0 )
                arrayToUpload +=  i!=imgArray.length - 1 ? url +  "," : url ;
            else
                arrayToUpload +=  i!=imgArray.length - 1 ? imgArray[i] + "," : imgArray[i];
            System.out.println(arrayToUpload);
            System.out.println();
        }

        if(imgToEdit==-1)
            arrayToUpload += ',' + url;  // for adding new



        System.out.println("string for upload:  " + arrayToUpload);
*/

        return url;

    }






}
