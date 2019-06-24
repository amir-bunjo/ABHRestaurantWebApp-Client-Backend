package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Reservation;
import com.abhrestaurant.clientserver.model.Restaurant;
import com.abhrestaurant.clientserver.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TableRepository extends JpaRepository<Table,Long> {

    @Query(value = "SELECT COUNT(*) u FROM tables u WHERE u.restaurant_id = ?1 and u.available = ?2", nativeQuery = true )
    Long findCountOfAvailableTables(Long restaurantId,Boolean a);

    List<Table> findTableBySeatsAndRestaurant(Integer seats, Restaurant restaurant);
}
