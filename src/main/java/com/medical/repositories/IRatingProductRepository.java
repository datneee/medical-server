package com.medical.repositories;

import com.medical.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingProductRepository extends JpaRepository<Rating, Integer> {


}
