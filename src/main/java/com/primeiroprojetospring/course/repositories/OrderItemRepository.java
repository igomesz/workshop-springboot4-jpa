package com.primeiroprojetospring.course.repositories;

import com.primeiroprojetospring.course.entities.OrderItem;
import com.primeiroprojetospring.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
