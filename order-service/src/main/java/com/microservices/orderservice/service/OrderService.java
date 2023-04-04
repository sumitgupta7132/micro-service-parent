package com.microservices.orderservice.service;

import com.microservices.orderservice.common.dto.OrderLineItemsDto;
import com.microservices.orderservice.common.dto.OrderRequest;
import com.microservices.orderservice.common.dto.OrderResponse;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderResponse placeOrder(OrderRequest orderRequest){
        log.info("Request received for placing an order");
        Order order = orderRepository.save(getOrderModel(orderRequest));
        return getOrderResponse(order);
    }

    private OrderResponse getOrderResponse(Order order) {
        List<OrderLineItemsDto> orderLineItemsDtos = order.getOrderLineItems()
                .stream()
                .map(this::mapToOrderResponse)
                .toList();
        return OrderResponse.builder().orders(orderLineItemsDtos).build();
    }

    private OrderLineItemsDto  mapToOrderResponse(OrderLineItems orderLineItems) {
        return OrderLineItemsDto.builder()
                .id(orderLineItems.getId())
                .price(orderLineItems.getPrice())
                .skuCode(orderLineItems.getSkuCode())
                .quantity(orderLineItems.getQuantity())
                .build();
    }

    private Order getOrderModel(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrders()
                .stream()
                .map(this::mapToOrderLineItems)
                .toList();
        order.setOrderLineItems(orderLineItems);
        return order;
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
