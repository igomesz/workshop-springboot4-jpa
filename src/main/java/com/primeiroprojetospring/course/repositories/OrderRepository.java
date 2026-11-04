package com.primeiroprojetospring.course.repositories;

import com.primeiroprojetospring.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
