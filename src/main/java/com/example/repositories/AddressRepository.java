package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.UserAddress;
import com.example.entities.UserRecord;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Integer>{

}
