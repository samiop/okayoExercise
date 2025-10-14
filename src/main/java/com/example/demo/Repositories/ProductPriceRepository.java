package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.ProductPrice;

import java.time.LocalDate;
import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

  @Query("""
    select p from ProductPrice p
     where p.product.id = :productId
       and p.validFrom <= :onDate
       and (p.validTo is null or p.validTo >= :onDate)
     order by p.validFrom desc
    """)
 List<ProductPrice> findValidPrice(@Param("productId") Long productId,
                                    @Param("onDate") LocalDate onDate);
  List<ProductPrice> findByProductIdOrderByValidFromDesc(Long productId);
}
