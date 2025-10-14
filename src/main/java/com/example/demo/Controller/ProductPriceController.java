package com.example.demo.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repositories.ProductPriceRepository;
import com.example.demo.domain.ProductPrice;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/product-prices")
@RequiredArgsConstructor
public class ProductPriceController {

  private final ProductPriceRepository repo;

  @GetMapping("/product/{productId}")
  public List<ProductPrice> listByProduct(@PathVariable Long productId) {
    return repo.findByProductIdOrderByValidFromDesc(productId);
  }

  @GetMapping("/product/{productId}/valid")
  public List<ProductPrice> validOn(
      @PathVariable Long productId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate on) {
    return repo.findValidPrice(productId, on);
  }

  @PostMapping
  public ResponseEntity<ProductPrice> create(@RequestBody ProductPrice price) {
    var saved = repo.save(price);
    return ResponseEntity.created(URI.create("/api/product-prices/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductPrice> update(@PathVariable Long id, @RequestBody ProductPrice price) {
    return repo.findById(id).map(ex -> {
      price.setId(id);
      return ResponseEntity.ok(repo.save(price));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
