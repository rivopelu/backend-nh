package com.lewatihari.entities.repositories;

import com.lewatihari.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsBySlug(String slug);
}
