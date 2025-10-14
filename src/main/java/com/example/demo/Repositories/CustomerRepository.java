package com.example.demo.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Optional<Customer> findByCode(String code);
}