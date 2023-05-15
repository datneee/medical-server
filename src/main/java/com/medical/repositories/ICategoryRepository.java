package com.medical.repositories;

import com.medical.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    Category findByName(String name);
    Category findCategoryById(Integer id);
    boolean existsByName(String name);
}
