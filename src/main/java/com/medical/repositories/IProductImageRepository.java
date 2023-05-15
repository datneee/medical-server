package com.medical.repositories;

import com.medical.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageRepository extends JpaRepository<ProductImages, Integer> {
}
