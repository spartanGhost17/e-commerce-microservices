package com.ecommerce.order.service;

import com.ecommerce.order.controller.CustomerClient;
import com.ecommerce.order.controller.ProductClient;
import com.ecommerce.order.dto.OrderDto;
import com.ecommerce.order.exception.BusinessException;
import com.ecommerce.order.kafka.OrderConfirmation;
import com.ecommerce.order.kafka.OrderProducer;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.record.OrderLineRequest;
import com.ecommerce.order.record.OrderRequest;
import com.ecommerce.order.record.PurchaseRequest;
import com.ecommerce.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public OrderDto createOrder(OrderRequest orderRequest) {
        //check customer exists (OpenFeign)
        var customer = this.customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(
                () -> new BusinessException("Cannot create order. no customer exists with the provided id")
        );

        //purchase product with product micro-service (RestTemplate)
        var purchaseProducts = this.productClient.purchaseProducts(orderRequest.products());

        //persist order
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        //persist order lines
        for(PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity())
            );
        }

        //TODO: start payment process

        // Send the order confirmation  --> notification-message (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchaseProducts

        ));
        return orderMapper.toOrderDto(order);//TODO: should just return the order id
    }

    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderDto).collect(Collectors.toList());
    }

    public OrderDto findById(Integer orderId) {
        return orderRepository.findById(orderId).map(orderMapper::toOrderDto).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cannot find order with the provided id %d", orderId))
        );
    }
}
