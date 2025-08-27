package com.lbritosan.grokshop.repository;

import com.lbritosan.grokshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Exemplo de query custom
    List<Product> findByCategories_Name(String categoryName);
}