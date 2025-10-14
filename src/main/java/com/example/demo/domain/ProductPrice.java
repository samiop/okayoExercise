package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product_prices",uniqueConstraints = @UniqueConstraint(name = "uk_product_price_period",columnNames = {"product_id", "valid_from"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductPrice {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "unit_ht", nullable = false, precision = 19, scale = 2)
  private BigDecimal unitHt;

  @Column(name = "valid_from", nullable = false)
  private LocalDate validFrom;

  @Column(name = "valid_to")
  private LocalDate validTo; 
}

