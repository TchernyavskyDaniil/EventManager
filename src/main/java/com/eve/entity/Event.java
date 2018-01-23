package com.eve.entity;

import com.eve.util.DateUtil;
import com.eve.web.dto.EventDto;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name = "description")
    private String description;

    public Event(){}

    public Event(String name, Date date, String description){
        this.date = date;
        this.description = description;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
