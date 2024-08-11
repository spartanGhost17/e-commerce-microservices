package com.ecommerce.product.mapper;

import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductPurchaseDto;
import com.ecommerce.product.model.Category;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.record.ProductRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder().id(request.categoryId()).build())
                .build();
    }

    public ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getSerialNumber(),
                product.getCategory(),
                //product.getCategory().getId(),
                //product.getCategory().getName(),
                //product.getCategory().getDescription(),
                product.getImages()
        );
    }

    public ProductPurchaseDto toProductPurchaseDto(Product product, @NotNull(message = "quantity is required") double quantity) {
        return new ProductPurchaseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity,
                product.getImages()
        );
    }
}
