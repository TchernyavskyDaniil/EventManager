package com.eve.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled = false;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private Set<Role> roles;

//    @OneToMany(mappedBy = "owner")
//    private Set<Event> events;

    @OneToOne(mappedBy = "user")
    private VerificationToken token;

    public User(){
        this.enabled=false;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled=false;
    }

    public User(String username, String password, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.enabled=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void enable(){
        enabled = true;
    }

    public void disable(){
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public VerificationToken getToken() {
        return token;
    }

    public void setToken(VerificationToken token) {
        this.token = token;
    }

//    public Set<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(Set<Event> events) {
//        this.events = events;
//    }
}
