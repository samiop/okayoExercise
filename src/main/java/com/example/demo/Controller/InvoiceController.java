package com.example.demo.Controller;


import com.example.demo.domain.Invoice;
import com.example.demo.domain.InvoiceLine;
import com.example.demo.repositories.InvoiceLineRepository;
import com.example.demo.repositories.InvoiceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
//récupération des deux entités invoice et invoiceline  de la base de données en utilisant le repository
  private final InvoiceRepository invoiceRepo;
  private final InvoiceLineRepository lineRepo;

  @GetMapping public List<Invoice> list() { return invoiceRepo.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Invoice> get(@PathVariable Long id) {
    return invoiceRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/by-ref/{ref}")
  public ResponseEntity<Invoice> byRef(@PathVariable String ref) {
    return invoiceRepo.findByRefNumber(ref).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/by-customer/{customerId}")
  public List<Invoice> byCustomer(@PathVariable Long customerId) {
    return invoiceRepo.findByCustomerIdOrderByIssueDateDesc(customerId);
  }

  @GetMapping("/{invoiceId}/lines")
  public List<InvoiceLine> lines(@PathVariable Long invoiceId) {
    return lineRepo.findByInvoiceId(invoiceId).stream()
        .sorted(Comparator.comparing(InvoiceLine::getId)).toList();
  }

  @PostMapping
  public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
    // assurer le rattachement des lignes
    if (invoice.getLines() != null) {
      invoice.getLines().forEach(l -> l.setInvoice(invoice));
    }
    var saved = invoiceRepo.save(invoice);
    return ResponseEntity.created(URI.create("/api/invoices/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody Invoice invoice) {
    return invoiceRepo.findById(id).map(ex -> {
      invoice.setId(id);
      if (invoice.getLines() != null) {
        invoice.getLines().forEach(l -> l.setInvoice(invoice));
      }
      return ResponseEntity.ok(invoiceRepo.save(invoice));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!invoiceRepo.existsById(id)) return ResponseEntity.notFound().build();
    invoiceRepo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}