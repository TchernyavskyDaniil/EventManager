package com.eve.repository;

import com.eve.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address,Long> {

    Address findByName(String name);
    Address findByCountry(String country);
    Address findByCity(String city);
    Address findByCountryAndCityAndStreetAndName(String country,String city,String Street, String name);

    @Override
    void delete(Address role);
}
