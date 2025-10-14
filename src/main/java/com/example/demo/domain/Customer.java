package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 32, nullable = false, unique = true)
  private String code;

  @Column(length = 255, nullable = false)
  private String name;

  @Column(length = 255) private String address1;
  @Column(length = 255) private String address2;
  @Column(length = 20)  private String postalCode;
  @Column(length = 100) private String city;
  @Column(length = 100) private String country;
  @Column(length = 50)  private String phone;
  @Column(length = 255) private String email;
}