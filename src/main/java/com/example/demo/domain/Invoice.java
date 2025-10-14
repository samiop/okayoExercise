package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ref_number", length = 32, nullable = false, unique = true)
  private String refNumber;

  @Column(name = "issue_date", nullable = false)
  private LocalDate issueDate;

  @Column(name = "due_date")
  private LocalDate dueDate;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(name = "total_ht", nullable = false, scale = 2)
  @Builder.Default
  private BigDecimal totalHt = BigDecimal.ZERO;

  @Column(name = "total_ttc", nullable = false, scale = 2)
  @Builder.Default
  private BigDecimal totalTtc = BigDecimal.ZERO;

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<InvoiceLine> lines = new ArrayList<>();

  @Column(name = "created_at", nullable = false)
  @Builder.Default
  private OffsetDateTime createdAt = OffsetDateTime.now();
}