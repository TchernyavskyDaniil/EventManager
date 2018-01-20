package com.eve.repository;

import com.eve.entity.Address;
import com.eve.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address,Long> {

    Address findByName(String name);
    Address findByCountry(String country);
    Address findByCity(String city);

    @Override
    void delete(Address role);
}
