package com.medical.repositories;

import com.medical.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IProductRateRepository extends JpaRepository<Rating, Integer>, JpaSpecificationExecutor<Rating> {
    List<Rating> findByProductId(Integer productId);
}
