package com.eve.web.dto;

import com.eve.entity.Address;
import com.eve.entity.Event;
import com.eve.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

public class EventDto {

    private Long id;
    private String name;
    private String owner;
    private String description;
    private String address;
    private String date;

    public EventDto(){

    }
    public EventDto(Event e){
        setId(e.getId());
        setAddress(e.getAddress()==null?null:e.getAddress().toString());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
