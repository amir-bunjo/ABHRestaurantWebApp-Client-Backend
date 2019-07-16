package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Reservation;
import com.abhrestaurant.clientserver.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation,Long> {

    @Query(value="SELECT count(*) FROM reservation WHERE date = ?1  AND restaurant_id = ?2 ", nativeQuery = true)
    Long getCountOfReservation(Date date, Long restaurantId);

    @Query(value="SELECT * FROM reservation WHERE restaurant_id=?1 AND date = ?2", nativeQuery = true)
    List<Reservation> getReservationByDate(Long restaurantId,Date date);

    List<Reservation> findReservationByDateAndGuestNumberAndRestaurant(Date date, Integer guestNumber, Restaurant restaurant);

    List<Reservation> findReservationById(Long id);


    @Query(value="SELECT * FROM reservation WHERE date = ?1  AND restaurant_id = ?2 AND ?3 < time AND time < ?4 AND guest_number = ?5 ORDER BY time ASC", nativeQuery = true)
    List<Reservation> getReservationInTimeScope(Date date, Long restaurantId, Time startTime, Time endTime, Integer guestNumber);

    @Modifying
    @Query(value="DELETE FROM reservation WHERE restaurant_id=?1", nativeQuery = true)
    int deleteByRestaurant(Long restaurantId);

}
