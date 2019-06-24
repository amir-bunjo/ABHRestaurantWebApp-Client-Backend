package com.abhrestaurant.clientserver.controller;

import com.abhrestaurant.clientserver.exception.ResourceNotFoundException;
import com.abhrestaurant.clientserver.model.Reservation;
import com.abhrestaurant.clientserver.model.Table;
import com.abhrestaurant.clientserver.repository.ReservationRepository;
import com.abhrestaurant.clientserver.repository.RestaurantRepository;
import com.abhrestaurant.clientserver.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TableController {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/alltables")
    public List<Table> getAllTables(){

        System.out.println("Should be getted all reservation");
        return tableRepository.findAll();
    }

    @PostMapping("/table")
    public  List<Table> saveTables(@RequestBody Table table){

        tableRepository.save(table);
        System.out.println("Should be saved reservation");
        return tableRepository.findAll();
    }

    @GetMapping("/table/available/count/{restaurantId}")
    public  Long getCountOfAvailable(@PathVariable Long restaurantId ){

        System.out.println("Should be getted available tables");
        return tableRepository.findCountOfAvailableTables(restaurantId,true);
    }
/*
    @PostMapping("/reservation/available/inrange/{restaurantId}")
    public List<Time> getCountOfAvailableToday(@PathVariable Long restaurantId, @RequestBody Time time){


    }       */

    @GetMapping("/table/restaurant/seats/{restaurantId}/{seats}/{dateString}/{timeString}")
    public  List<Table> getTablesBySeat(@PathVariable Long restaurantId, @PathVariable Integer seats , @PathVariable String dateString, @PathVariable String timeString){

        System.out.println("Should be getted available tables");

        //Date date = new Date(2019,06,21);

        Time selectedTime = Time.valueOf(timeString);
        Time startTime = Time.valueOf(timeString);
        Time endTime = Time.valueOf(timeString);

        startTime.setHours(selectedTime.getHours() -2);
        endTime.setHours(selectedTime.getHours() + 3);

        Date selectedDate = Date.valueOf(dateString);


       // System.out.println(reservations);

        return restaurantRepository.findById(restaurantId).map(restaurant -> {

            List<Table> tableList = tableRepository.findTableBySeatsAndRestaurant(seats, restaurant);
            int i=0;
            for(Table table: tableList){
                i=0;
                List<Reservation> reservationList = table.getReservations();
                List<Reservation> found = new ArrayList<Reservation>();

                for(Reservation reservation: reservationList){

                    if(!reservation.getDate().equals(selectedDate)|| !(reservation.getTime().after(startTime) && reservation.getTime().before(endTime)) )
                        found.add(reservation);

                    i++;
                }
                reservationList.removeAll(found);
                Collections.sort(reservationList);
            }

            return tableList;
        }).orElseThrow(() -> new ResourceNotFoundException("Restaurant " + restaurantId + " not found"));
    }

}