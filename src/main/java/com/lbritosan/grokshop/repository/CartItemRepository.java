package com.lbritosan.grokshop.repository;

import com.lbritosan.grokshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
