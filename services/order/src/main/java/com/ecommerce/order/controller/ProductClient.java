package com.ecommerce.order.controller;

import com.ecommerce.order.dto.PurchaseDto;
import com.ecommerce.order.exception.BusinessException;
import com.ecommerce.order.model.HttpResponse;
import com.ecommerce.order.record.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseDto> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        //ParameterizedTypeReference<List<PurchaseDto>> responseType = new ParameterizedTypeReference<>() {};//will ask to parse the response using this
        ParameterizedTypeReference<HttpResponse> responseType = new ParameterizedTypeReference<>() {};//will ask to parse the response using this

        ResponseEntity<HttpResponse> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                POST,
                requestEntity,
                responseType
        );

        if(responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: "+responseEntity.getStatusCode());
        }

        // Extract the HttpResponse object from the response entity
        HttpResponse responseBody = responseEntity.getBody();

        // Verify that the response body and its data field are not null
        if (responseBody == null || responseBody.getData() == null) {
            throw new BusinessException("Unexpected response structure received from the product service. No data was returned by purchase service");
        }

        // Extract the 'products' list from the data map, ensuring the correct type
        @SuppressWarnings("unchecked")
        List<PurchaseDto> products = (List<PurchaseDto>) responseBody.getData().get("products");

        if (products == null) {
            throw new BusinessException("The 'products' key is missing or the data structure is incorrect.");
        }

        return products;//responseEntity.getBody();
    }
}
