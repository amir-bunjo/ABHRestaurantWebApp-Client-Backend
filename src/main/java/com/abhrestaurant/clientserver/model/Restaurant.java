package com.abhrestaurant.clientserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="restaurants")
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

    @Column(name="photos")
    private String photos;

    @Column(name="promo_photo")
    private String promophoto;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @OneToMany
    @JoinColumn(name = "restaurant_id" , referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "restaurant" })
    private List<com.abhrestaurant.clientserver.model.Table> tables;

    public Restaurant() {}

    public Restaurant(Long id, Long cityId, String name, String street, String description, String photos, String promophoto, String latitude, String longitude) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.street = street;
        this.description = description;
        this.photos = photos;
        this.promophoto = promophoto;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getPromophoto() {
        return promophoto;
    }

    public void setPromophoto(String promophoto) {
        this.promophoto = promophoto;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<com.abhrestaurant.clientserver.model.Table> getTables() {
        return tables;
    }

    public void setTables(List<com.abhrestaurant.clientserver.model.Table> tables) {
        this.tables = tables;
    }


}
