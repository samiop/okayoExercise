package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
  Optional<Invoice> findByRefNumber(String refNumber);
  List<Invoice> findByCustomerIdOrderByIssueDateDesc(Long customerId);
}