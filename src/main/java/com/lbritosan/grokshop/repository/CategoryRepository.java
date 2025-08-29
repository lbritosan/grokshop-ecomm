package com.lbritosan.grokshop.repository;

import com.lbritosan.grokshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Query customizada para buscar categoria por nome
    Category findByName(String name);
}
