package com.medical.repositories;

import com.medical.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImagesRepository extends JpaRepository<ProductImages, Integer> {
    ProductImages findByImageUrl(String imageUrl);

    void deleteByProductId(Integer productId);
}
