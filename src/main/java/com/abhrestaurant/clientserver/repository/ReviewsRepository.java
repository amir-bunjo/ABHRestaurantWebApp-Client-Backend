package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {

    @Query(value = "SELECT u.mark FROM reviews u WHERE u.restaurant_id = ?1 and u.user_id = ?2", nativeQuery = true )
    Float findReview(Long restaurantId, Long userId);
}
