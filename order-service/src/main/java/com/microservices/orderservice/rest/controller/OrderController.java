package com.microservices.orderservice.rest.controller;

import com.microservices.orderservice.common.dto.OrderRequest;
import com.microservices.orderservice.common.dto.OrderResponse;
import com.microservices.orderservice.rest.response.HttpApiResponse;
import com.microservices.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpApiResponse<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return new HttpApiResponse<>(orderResponse);
    }
}
