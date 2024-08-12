package com.ecommerce.order.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductImage {
    private Integer id;
    private String imageUrl;
    private Object product;
}
