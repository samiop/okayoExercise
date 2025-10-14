package com.example.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repositories.ProductRepository;
import com.example.demo.domain.Product;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductRepository repo;

  @GetMapping public List<Product> list() { return repo.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Product> get(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/by-sku/{sku}")
  public ResponseEntity<Product> bySku(@PathVariable String sku) {
    return repo.findBySku(sku).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product p) {
    var saved = repo.save(p);
    return ResponseEntity.created(URI.create("/api/products/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product p) {
    return repo.findById(id).map(ex -> {
      p.setId(id);
      return ResponseEntity.ok(repo.save(p));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
