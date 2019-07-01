package com.abhrestaurant.clientserver.repository;


import com.abhrestaurant.clientserver.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    List<Restaurant> findFirst2ByName(String name);

    Restaurant findRestaurantById(Long id);

    @Query(value = "SELECT * FROM restaurants ORDER BY id LIMIT 9 OFFSET ?1 ", nativeQuery = true)
    List<Restaurant> findRestaurantsWithStartIndex( Long numberofvalues );

    @Query(value= "SELECT count(*) FROM restaurants", nativeQuery = true)
    Long getTableLength();

    @Query(value= " SELECT * FROM restaurants WHERE name ILIKE %?1% ", nativeQuery = true)
    List<Restaurant> getMatchedRestaurants(String matchpatern);
}
