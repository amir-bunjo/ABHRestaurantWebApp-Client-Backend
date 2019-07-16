package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {

    @Query(value = "SELECT * FROM reviews u WHERE u.restaurant_id = ?1 and u.user_id = ?2", nativeQuery = true )
    Reviews findReview(Long restaurantId, Long userId);

    Reviews findByRestaurantId(Long restaurantId);

    @Query(value = "SELECT AVG(mark) FROM reviews WHERE  restaurant_id = ?1 ", nativeQuery = true )
    Float getAverageOfRevievByRestaurantId(Long restaurantId);

    @Modifying
    @Query(value = "DELETE FROM reviews WHERE restaurant_id = ?1 ", nativeQuery = true )
    int deleteReviewsByRestaurant(Long restaurantId);


}
