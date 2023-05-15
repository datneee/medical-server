package com.medical.repositories;

import com.medical.entity.OrderItem;
import com.medical.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product , Integer> , JpaSpecificationExecutor<Product> {

    Product findProductById(Integer id);

    void deleteProductById(Integer id);

    @Query(value = "SELECT * FROM products WHERE ticketId IS NOT NULL;", nativeQuery = true)
    List<Product> getListSpecialProduct();

    @Modifying
    @Query(value = "DELETE FROM Product p WHERE p.id = :id")
    void deleteById(@Param("id") Integer id);

    List<Product> findByCategoryId(Integer id);
    Product findProductByTitle(String title);

    boolean existsProductByTitle(String title);

    @Query(value = "SELECT o FROM OrderItem o GROUP BY o.product.id ORDER BY COUNT(o.id) DESC", nativeQuery = false)
    List<OrderItem> getListFeatureProduct();
}

