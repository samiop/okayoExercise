package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
  //pour l'auto incrementation de l'id
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // nullable = false pour que cette colonne ne puisse pas etre nulle dans la base de données

  @Column(length = 64, nullable = false, unique = true)
  private String sku;
  // nullable = false pour que cette colonne ne puisse pas etre nulle dans la base de données

  @Column(length = 255, nullable = false)
  private String name;
  ///
}