package com.microservices.orderservice.common.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderResponse {
    List<OrderLineItemsDto> orders;
}
