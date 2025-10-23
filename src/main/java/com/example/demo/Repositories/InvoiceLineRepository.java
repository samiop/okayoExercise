package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.InvoiceLine;

import java.util.List;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long> {
  List<InvoiceLine> findByInvoiceId(Long invoiceId);
}