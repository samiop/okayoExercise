package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "invoice_lines")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InvoiceLine {
   //pour l'auto incrementation de l'id
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "invoice_id", nullable = false)
  private Invoice invoice;

  //  produit va etre traçable pour qu'on puisse  qu'on puisse l'indentifier dans la base de données
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

 
  @Column(nullable = false, length = 255)
  private String designation; 

  @Column(name = "vat_rate_pct", nullable = false, scale = 2)
  private BigDecimal vatRatePct; 

  @Column(name = "unit_ht", nullable = false, scale = 2)
  private BigDecimal unitHt;

  @Column(nullable = false, scale = 3)
  private BigDecimal quantity;

  // nullable = false pour que cette colonne ne puisse pas etre nulle dans la base de données
  @Column(name = "line_total_ht", nullable = false, scale = 2)
  private BigDecimal lineTotalHt; // unit_ht * quantity

}