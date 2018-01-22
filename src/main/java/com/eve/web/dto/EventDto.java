package com.eve.web.dto;

import com.eve.entity.Event;
import com.eve.util.DateUtil;

import java.util.Date;

public class EventDto {

    private Long id;
    private String name;
    private String owner;
    private String description;
    private String country;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    private String city;
    private String street;

    private Double x;

    private Double y;
    private String date;
    public EventDto(){

    }

    public EventDto(Event e){
        setId(e.getId());
        setCountry(e.getAddress().getCountry());
        setCity(e.getAddress().getCity());
        setStreet(e.getAddress().getStreet());

        setDate(e.getDate()==null? null:e.getDate());
        setDescription(e.getDescription()==null? null: e.getDescription());
        setName(e.getName());
        setOwner(e.getOwner().getUsername()==null? null:e.getOwner().getUsername());
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setDate(Date date) {
        this.date = DateUtil.SDF.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
