package com.medical.repositories;

import com.medical.entity.CategoryImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryImageRepository  extends JpaRepository<CategoryImage, Integer> {
    void deleteByCategoryId(Integer categoryId);
}
