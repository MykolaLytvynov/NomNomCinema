package ua.mykola.orderservice.rest.dto;

import ua.mykola.commons.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        int hall,
        int row,
        int seat,
        LocalDateTime createdAt,
        List<OrderItem> items
) {}
