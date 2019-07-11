package com.abhrestaurant.clientserver.controller;

import com.abhrestaurant.clientserver.exception.ResourceNotFoundException;
import com.abhrestaurant.clientserver.model.Reservation;
import com.abhrestaurant.clientserver.repository.ReservationRepository;
import com.abhrestaurant.clientserver.repository.RestaurantRepository;
import com.abhrestaurant.clientserver.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    TableRepository tableRepository;

    @GetMapping("/allreservation")
    public List<Reservation> getAllRestaurant(){

        System.out.println("Should be getted all reservation");
        return reservationRepository.findAll();
    }

    @PostMapping("/reservation/{restaurantId}/{tableId}")
    public  Boolean saveReservation(@PathVariable Long restaurantId,@PathVariable Long tableId,@RequestBody Reservation reservation){

        //reservationRepository.save(reservation);
        //restaurantRepository.findById(restaurantId).map(restaurant -> reservation.setRestaurant(restaurant) );
        System.out.println("Should be saved reservation " + reservation.getTime() + reservation.getDate() + reservation.getGuestNumber());
        //return reservationRepository.findAll();

        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            reservation.setRestaurant(restaurant);

            reservation.setTable(tableRepository.findById(tableId).get());

            return true;


        }).orElseThrow(() -> new ResourceNotFoundException("Restaurant " + restaurantId + " not found"));
    }

    @GetMapping("/reservation/available/count/{restaurantId}/{dateString}")
    public Long getCountOfAvailableToday(@PathVariable Long restaurantId, @PathVariable String dateString){

        Date date = Date.valueOf(dateString);
       //  new Date(date.getDayOfMonth(),date.getMonthValue(),date.getYear())

        System.out.println(date + "resID: " + restaurantId);
        System.out.println("Should be getted reservation available count ");
        return reservationRepository.getCountOfReservation(date,restaurantId);
       // return new Long(2);
    }


    @GetMapping("/reservations/bydate/{dateString}/byguest/{guestNumber}/bytime/{timeString}/{restaurantId}")
    public List<Reservation> getAllReservation(@PathVariable String dateString, @PathVariable Integer guestNumber, @PathVariable Long restaurantId, @PathVariable String timeString){

        Date date = Date.valueOf(dateString) ;
        System.out.println("Should be getted all reservation with matched parameters" +timeString);

      //  List<Reservation> reservationList ;
        LocalTime parsedTime = LocalTime.parse(timeString);



        return restaurantRepository.findById(restaurantId).map(restaurant -> {

            List<Reservation> reservationList = reservationRepository.findReservationByDateAndGuestNumberAndRestaurant(date,guestNumber, restaurant);

            for(Reservation reservation: reservationList){
                Time time = reservation.getTime();
                System.out.println("Rezervacijsko vrijeme" +time.toString());
            }


            return reservationList;

        }).orElseThrow(() -> new ResourceNotFoundException("Restaurant " + restaurantId + " not found"));
    }


    @GetMapping("/reservations/restaurant/{restaurantId}/timescope/{timeString}/bydate/{dateString}/byguest/{guestNumber}")
    public List<Reservation> getReservationsInScope(@PathVariable String dateString, @PathVariable Long restaurantId, @PathVariable String timeString,@PathVariable Integer guestNumber){

        Date date = Date.valueOf(dateString) ;

        System.out.println(dateString);

        Time selectedTime = Time.valueOf(timeString);
        Time startTime = Time.valueOf(timeString);
        Time endTime = Time.valueOf(timeString);

        startTime.setHours(selectedTime.getHours() -2);
        endTime.setHours(selectedTime.getHours() + 3);

        //  List<Reservation> reservationList ;
        LocalTime parsedTime = LocalTime.parse(timeString);



            List<Reservation> reservationList = reservationRepository.getReservationInTimeScope(date, restaurantId,startTime,endTime,guestNumber);


            return reservationList;



    }







    /*
    @PostMapping("/reservation/bydate{restaurantId}")
    public List<Reservation> getRestaurantReservationByDate(@PathVariable Long restaurantId, @RequestBody LocalDate localDate){

       // LocalDate localDate1 = new LocalDate("")

       // return reservationRepository.getReservationByDate(restaurantId,localDate)
        // return new Long(2);
    }

    1. Create function for getting Tables with numberOfSeats which support sharing resID in path variable and number of seats(path or body)
    2.Create array in angular for saving type of seats(etc. 4people,2 people),
    3.Make a function in angular or java for parsing result from 1.step  and  predicting available times to reserve.

*/


}
