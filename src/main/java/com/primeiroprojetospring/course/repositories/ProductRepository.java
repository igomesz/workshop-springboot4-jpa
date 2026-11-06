package com.primeiroprojetospring.course.repositories;

import com.primeiroprojetospring.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
