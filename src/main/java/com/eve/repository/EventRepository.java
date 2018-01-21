package com.eve.repository;

import com.eve.entity.Event;
import com.eve.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface EventRepository extends JpaRepository<Event,Long> {

    Event findByName(String name);
    Event findById(Long id);
    Set<Event> findByOwnerId(Long ownerId);
    Set<Event> findByAddressId_Country(String country);



    @Override
    void delete(Event role);
}
