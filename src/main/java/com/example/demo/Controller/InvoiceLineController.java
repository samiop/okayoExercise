package com.example.demo.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repositories.InvoiceLineRepository;
import com.example.demo.domain.InvoiceLine;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-lines")
@RequiredArgsConstructor
public class InvoiceLineController {

  private final InvoiceLineRepository repo;

  @GetMapping("/{id}")
  public ResponseEntity<InvoiceLine> get(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/by-invoice/{invoiceId}")
  public List<InvoiceLine> byInvoice(@PathVariable Long invoiceId) {
    return repo.findByInvoiceId(invoiceId);
  }

  @PostMapping
  public InvoiceLine create(@RequestBody InvoiceLine line) { return repo.save(line); }

  @PutMapping("/{id}")
  public ResponseEntity<InvoiceLine> update(@PathVariable Long id, @RequestBody InvoiceLine line) {
    return repo.findById(id).map(ex -> {
      line.setId(id);
      return ResponseEntity.ok(repo.save(line));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
