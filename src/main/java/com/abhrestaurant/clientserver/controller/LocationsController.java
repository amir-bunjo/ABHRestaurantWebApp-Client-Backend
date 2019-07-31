package com.abhrestaurant.clientserver.controller;


import com.abhrestaurant.clientserver.model.Location;
import com.abhrestaurant.clientserver.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocationsController {

    @Autowired
    LocationsRepository locationsRepository;

    @GetMapping("/alllocations")
    public List<Location> getAllLocations(){

        System.out.println("Should be getted all locations");

        return locationsRepository.findAll();
    }

    @PostMapping("/save/location")
    public Location saveLocation(@RequestBody Location location){

        System.out.println("Should be saved location: " + location.getName());

        return locationsRepository.save(location);

    }

    @DeleteMapping("/delete/location/{locationId}")
    public boolean deleteLocation(@PathVariable Long locationId){

        locationsRepository.deleteById(locationId);
        return true;
    }

    @GetMapping("/get/location/{locationId}")
    public Location getLocationById(@PathVariable Long locationId){

        System.out.println("Should be getted location: "+ locationId);
        return locationsRepository.getOne(locationId);
    }

    @GetMapping("/get/count/locations")
    public Long getCountOfLocations(){

        return locationsRepository.getCountOfLocations();
    }


}


