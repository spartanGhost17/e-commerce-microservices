package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductPurchaseDto;
import com.ecommerce.product.exception.ProductPurchaseException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductImage;
import com.ecommerce.product.record.ProductPurchaseRequest;
import com.ecommerce.product.record.ProductRequest;
import com.ecommerce.product.repository.ImageRepository;
import com.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecommerce.product.constants.ApplicationConstants.BASE_PRODUCT_API_PATH;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ProductMapper productMapper;
    private final FileService fileService;

    public ProductDto createProduct(ProductRequest request) {
        log.info("Creating product {}", request);
        var product = productMapper.toProduct(request);
        String serialNumber = UUID.randomUUID().toString();
        product.setSerialNumber(serialNumber);
        productRepository.save(product);
        return productMapper.toProductDto(product);
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseDto> purchase(@Valid List<ProductPurchaseRequest> requests) {
        log.info("purchasing product");
        var productIds = requests.stream().map(ProductPurchaseRequest::productId).toList();//accumulate productIds
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Some Product were do not exists");
        }

        var storedRequest = requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        var purchasedProducts = new ArrayList<ProductPurchaseDto>();
        for(int i = 0; i < storedRequest.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Sorry, the product: "+product.getName()+" has "+product.getAvailableQuantity()+" items left in stock");
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseDto(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductDto findById(Integer productId) {
        log.info("find product by ID {}", productId);
        return productRepository.findById(productId)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException("Could not find product for ID "+ productId));
    }

    public List<ProductDto> findAll() {
        log.info("find all products");
        return productRepository.findAll().stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    /**
     * images
     * */
    public String addImage(MultipartFile file, Integer productId) {
        log.info("add image for product {}", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Could not find product for ID "+ productId));
        String imageUrl = fileService.saveProductImage(file, product.getSerialNumber());
        var productImage = ProductImage.builder()
                .imageUrl(imageUrl)
                .product(product)
                .build();
        imageRepository.save(productImage);
        return getUri("/image/"+imageUrl).toString();
    }

    public ProductImage deleteById(Integer imageId, Integer productId) {
        log.info("delete image ID: {} for product {}", imageId, productId);
        ProductDto product = findById(productId);
        ProductImage productImage = getImageById(imageId);
        int lastIndexOf = productImage.getImageUrl().lastIndexOf("/");
        String fileName = productImage.getImageUrl().substring(lastIndexOf+1);
        fileService.deleteProductImage(fileName, product.serialNumber());
        productRepository.deleteById(imageId);
        return productImage;
    }

    public List<ProductImage> getImages(Integer productId) {
        log.info("get all images for product {}", productId);
        return imageRepository.findByProductId(productId).stream().peek(imageProduct -> {
            String imageUrl = imageProduct.getImageUrl();
            imageProduct.setImageUrl(getUri("/image/"+imageUrl).toString());
        }).collect(Collectors.toList());
    }

    public void deleteAllImages(Integer productId) {
        log.info("delete all images for product {}", productId);
        List<ProductImage> images = getImages(productId);
        int lastIndexOf = images.get(0).getImageUrl().lastIndexOf("/");
        String folderName = images.get(0).getImageUrl().substring(0, lastIndexOf);
        fileService.deleteAllProductImages(folderName);
    }

    public byte[] getImage(String folderName, String fileName) {
        return fileService.getProductImage(folderName, fileName);
    }

    private ProductImage getImageById(Integer imageId) {
        log.info("get image by id {}", imageId);
        return imageRepository.findById(imageId).orElseThrow(() -> new EntityNotFoundException("Could not find the image for ID:: "+imageId));
    }

    private URI getUri(String fileUrl) {
        return URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(BASE_PRODUCT_API_PATH)
                .path(fileUrl)
                .toUriString());
    }
}
