package com.ecommerce.product.repository;

import com.ecommerce.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findByProductId(Integer productId);
}
