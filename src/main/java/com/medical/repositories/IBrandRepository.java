package com.medical.repositories;

import com.medical.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<Brand, Integer> {
    Brand getBrandById(Integer id);
}
