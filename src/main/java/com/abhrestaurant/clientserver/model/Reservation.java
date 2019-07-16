package com.abhrestaurant.clientserver.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name="reservation")
public class Reservation implements Comparable<Reservation>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


  //  private Long userId;

   // private Long tableId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @JsonIgnore
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    com.abhrestaurant.clientserver.model.Table table;


    @Column(name="time")
    private Time time; //change to Time type later

    @Column(name="date")
    private Date date; //change to Date type later

    @Column(name="guest_number")
    private Integer guestNumber;

    public Reservation() {}

    public Reservation(Long id, Time time, Date date, Integer guestNumber) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.guestNumber = guestNumber;
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

    public com.abhrestaurant.clientserver.model.Table getTable() {
        return table;
    }

    public void setTable(com.abhrestaurant.clientserver.model.Table table) {
        this.table = table;
    }


    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(Integer guestNumber) {
        this.guestNumber = guestNumber;
    }

    @Override
    public int compareTo(Reservation o) {
        return getTime().compareTo(o.getTime());
    }

}
