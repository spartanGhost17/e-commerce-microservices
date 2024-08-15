package com.ecommerce.product.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Column(name = "available_quantity")
    private double availableQuantity;
    private BigDecimal price;
    @Column(name = "serial_number")
    private String serialNumber;
    //one to many
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference //parent side of bidirectional relationship (include object in serialization)
    private List<ProductImage> images;

    //one to many
    @ManyToOne
    @JoinColumn(name = "category_id")//foreign key
    @JsonManagedReference
    //@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

}
