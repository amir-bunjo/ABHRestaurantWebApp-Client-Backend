package com.abhrestaurant.clientserver.repository;


import com.abhrestaurant.clientserver.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    List<Restaurant> findFirst2ByName(String name);

    Restaurant findRestaurantById(Long id);

    @Query(value = "SELECT * FROM restaurants ORDER BY id LIMIT 9 OFFSET ?1 ", nativeQuery = true)
    List<Restaurant> findRestaurantsWithStartIndex( Long startindex );

    @Query(value= "SELECT count(*) FROM restaurants", nativeQuery = true)
    Long getTableLength();

    @Query(value= " SELECT * FROM restaurants WHERE name ILIKE %?1% AND mark <= ?2 AND price_range <= ?3 ", nativeQuery = true)
    List<Restaurant> getMatchedRestaurants(String matchpatern,Float mark,Float price);

    @Query(value= " SELECT * FROM restaurants WHERE  mark <= ?1 AND price_range <= ?2  ", nativeQuery = true)
    List<Restaurant> getMatchedRestaurantsByMarkAndPrice(Float mark,Float price);

    @Query(value= " SELECT * FROM restaurants WHERE name ILIKE %?1%  ", nativeQuery = true)
    List<Restaurant> getAllRestaurantsWithMatchedName(String matchpatern);

    @Query(value= " SELECT id, name FROM restaurants ORDER BY id DESC  LIMIT 9 OFFSET ?1  ", nativeQuery = true)
    List<Object> getAllRestaurantsNameAndId( Long startindex);


}
