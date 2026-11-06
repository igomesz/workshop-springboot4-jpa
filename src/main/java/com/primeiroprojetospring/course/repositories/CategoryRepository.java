package com.primeiroprojetospring.course.repositories;

import com.primeiroprojetospring.course.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
