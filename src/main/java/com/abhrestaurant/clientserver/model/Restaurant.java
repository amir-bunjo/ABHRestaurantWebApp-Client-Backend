package com.abhrestaurant.clientserver.model;

import javax.persistence.*;

@Entity
@Table(name="restaurant")
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="city_id")
    private Long cityId;

    @Column(name="name")
    private String name;

    @Column(name="street")
    private String street;

    @Column(name="description")
    private String description;

    public Restaurant() {}

    public Restaurant(Long id, Long cityId, String name, String street, String description) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.street = street;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
