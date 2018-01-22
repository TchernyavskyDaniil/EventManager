package com.eve.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street_address")
    private String street;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "address")
    private Set<Event> events;

    public Address(){}
    public Address(String name, String country, String city, String street){
        this.country = country;
        this.name = name;
        this.city = city;
        this.street = street;
    }
    @Override
    public String toString(){
        String s = String.format("%s, %s, %s",country,city,street);
        return s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSity() {
        return city;
    }

    public void setSity(String sity) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
