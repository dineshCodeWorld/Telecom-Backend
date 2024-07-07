package com.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telecom.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
