package com.abhrestaurant.clientserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name="mark")
    private Float mark;

    @Column(name="votes")
    private Float votes;

    @Column(name="food_types")
    private String foodTypes;

    @Column(name="price_range")
    private Float priceRange;

    @Column(name="promo_photo")
    private String promophoto;

    @Column(name="cover_photo")
    private String coverphoto;

    @Column(name="latitude")
    private Float latitude;

    @Column(name="longitude")
    private Float longitude;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval=true)
    @JoinColumn(name = "restaurant_id" , referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "restaurant" })
    private List<com.abhrestaurant.clientserver.model.Table> tables;

    public Restaurant() {}

    public Restaurant(Long id, Long cityId, String name, String street, String description, String photos, Float mark, Float votes, Float priceRange, String foodTypes, String promophoto, Float latitude, Float longitude, List<com.abhrestaurant.clientserver.model.Table> tables) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.street = street;
        this.description = description;
        this.photos = photos;
        this.mark = mark;
        this.votes = votes;
        this.priceRange = priceRange;
        this.foodTypes = foodTypes;
        this.promophoto = promophoto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tables = tables;
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

    public String getCoverphoto() {
        return coverphoto;
    }

    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Float getVotes() {
        return votes;
    }

    public void setVotes(Float votes) {
        this.votes = votes;
    }

    public Float getPriceRange() {
        return priceRange;
    }

    public String getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(String foodTypes) {
        this.foodTypes = foodTypes;
    }

    public void setPriceRange(Float priceRange) {
        this.priceRange = priceRange;
    }

    public String getPromophoto() {
        return promophoto;
    }

    public void setPromophoto(String promophoto) {
        this.promophoto = promophoto;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public List<com.abhrestaurant.clientserver.model.Table> getTables() {
        return tables;
    }

    public void setTables(List<com.abhrestaurant.clientserver.model.Table> tables) {
        this.tables = tables;
    }


}
