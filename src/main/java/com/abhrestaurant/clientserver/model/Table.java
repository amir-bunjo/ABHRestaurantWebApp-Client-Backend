package com.abhrestaurant.clientserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Entity
@javax.persistence.Table(name="tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn (name="restaurant_id",referencedColumnName = "id")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name="seats")
    private Integer seats;

    @Column(name="available")
    private Boolean available;

    @Column(name="start_at")
    private String startAt;

    @Column(name="reservation_date")
    private String reservevationDate;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "table_id" , referencedColumnName = "id")
    @JsonIgnoreProperties(value = "table")
    private List<Reservation> reservations;

    public Table() {
    }

    public Table(Long id, Integer seats, Boolean available, String startAt, String reservevationDate) {
        this.id = id;
        this.seats = seats;
        this.available = available;
        this.startAt = startAt;
        this.reservevationDate = reservevationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
