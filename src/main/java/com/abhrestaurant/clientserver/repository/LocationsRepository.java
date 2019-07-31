package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationsRepository  extends JpaRepository<Location, Long> {

    @Query(value= "SELECT count(*) FROM locations", nativeQuery = true)
    Long getCountOfLocations();
}
