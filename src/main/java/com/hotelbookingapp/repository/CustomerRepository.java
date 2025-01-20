package com.hotelbookingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbookingapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByCustomerMobile(Long contactNo);
	Optional<Customer> findByCustomerEmail(String email);
	
}
