package com.abhrestaurant.clientserver.model;


import javax.persistence.*;

@Entity
@Table(name="users")
//@NamedQuery(name="User.findAll", query="SELECT a FROM User a")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    @Column(name="account_role")
    private String accountrole;

    public User() {
    }

    public User(Long id, String firstname, String lastname, String email, String country, String city, String password, String phone, String accountrole) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.country = country;
        this.city = city;
        this.password = password;
        this.phone = phone;
        this.accountrole = accountrole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountrole() {
        return accountrole;
    }

    public void setAccountrole(String accountrole) {
        this.accountrole = accountrole;
    }
}


