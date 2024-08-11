package com.ecommerce.product.controller;


import com.ecommerce.product.model.HttpResponse;
import com.ecommerce.product.record.ProductPurchaseRequest;
import com.ecommerce.product.record.ProductRequest;
import com.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.ecommerce.product.constants.ApplicationConstants.BASE_PRODUCT_API_PATH;

@RestController
@RequestMapping(value = BASE_PRODUCT_API_PATH)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<HttpResponse> createProduct(@RequestBody @Valid ProductRequest request) {

        return new ResponseEntity<>(HttpResponse.builder()
                .message("Product created")
                .data(Map.of("product", productService.createProduct(request)))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<HttpResponse> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> requests) {

        return new ResponseEntity<>(HttpResponse.builder()
                .message("Product purchased")
                .data(Map.of("products", productService.purchase(requests)))
                .build(), HttpStatus.OK);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<HttpResponse> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("productId") Integer productId) {

        return new ResponseEntity<>(HttpResponse.builder()
                .message("Image uploaded")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .data(Map.of("url", productService.addImage(file, productId)))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{productSerialNumber}/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] downloadImage(@PathVariable String productSerialNumber, @PathVariable String filename) {
        return productService.getImage(productSerialNumber, filename);
    }

    @GetMapping(value = "/images")
    public ResponseEntity<HttpResponse> getImages(@RequestParam("productId") Integer productId) {
        return new ResponseEntity<>(HttpResponse.builder()
                .message("Retrieved images")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("images", productService.getImages(productId)))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/images")
    public ResponseEntity<HttpResponse> deleteAllImage(@RequestParam("productId") Integer productId) {
        productService.deleteAllImages(productId);
        return new ResponseEntity<>(HttpResponse.builder()
                .message("Deleted all product Images")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/image")
    public ResponseEntity<HttpResponse> deleteImage(@RequestParam("imageId") Integer imageId, @RequestParam("productId") Integer productId) {
        return new ResponseEntity<>(HttpResponse.builder()
                .message("Deleted product Image")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("image", productService.deleteById(imageId, productId)))
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<HttpResponse> findProductById(@PathVariable("productId") Integer productId) {
        return new ResponseEntity<>(HttpResponse.builder()
                .message("Product found by Id")
                .data(Map.of("product", productService.findById(productId)))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HttpResponse> findAllProducts() {
        return new ResponseEntity<>(HttpResponse.builder()
                .message("All products")
                .data(Map.of("products", productService.findAll()))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }
}
