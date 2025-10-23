package com.example.demo.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerRepository repo;
//fetch tout les clients 
  @GetMapping public List<Customer> list() { return repo.findAll(); }

  //trouver un client par son id 
  @GetMapping("/{id}")
  public ResponseEntity<Customer> get(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

    //trouver client par code
  @GetMapping("/by-code/{code}")
  public ResponseEntity<Customer> byCode(@PathVariable String code) {
    return repo.findByCode(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
//ajouter un client
  @PostMapping
  public ResponseEntity<Customer> create(@RequestBody Customer c) {
    var saved = repo.save(c);
    return ResponseEntity.created(URI.create("/api/customers/"+saved.getId())).body(saved);
  }
  //modifier un client avec son id 
  @PutMapping("/{id}")
  public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer c) {
    return repo.findById(id).map(ex -> {
      c.setId(id);
      return ResponseEntity.ok(repo.save(c));
    }).orElse(ResponseEntity.notFound().build());
  }

  //supprimer un client avec son id
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}