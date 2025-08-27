package com.lbritosan.grokshop.repository;

import com.lbritosan.grokshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
