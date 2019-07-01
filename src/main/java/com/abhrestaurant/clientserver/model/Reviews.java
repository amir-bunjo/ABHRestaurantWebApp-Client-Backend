package com.abhrestaurant.clientserver.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn (name="user_id",referencedColumnName = "id")
    private User user;

    @Column(name="mark")
    private Float mark;

    @Column(name="restaurant_id")
    private Long restaurantId;

    @Column(name="comment")
    private String comment;

    public Reviews(){}

    public Reviews(Long id, Float mark, Long restaurantId, String comment) {
        this.id = id;
        this.mark = mark;
        this.restaurantId = restaurantId;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
