package com.ecommerce.order.service;

import com.ecommerce.order.dto.OrderLineDto;
import com.ecommerce.order.mapper.OrderLineMapper;
import com.ecommerce.order.model.OrderLine;
import com.ecommerce.order.record.OrderLineRequest;
import com.ecommerce.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public OrderLine saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order);
    }

    public List<OrderLineDto> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineDto).collect(Collectors.toList());
    }
}
