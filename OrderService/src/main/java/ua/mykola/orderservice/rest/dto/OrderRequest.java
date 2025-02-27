package ua.mykola.orderservice.rest.dto;

import ua.mykola.commons.model.OrderItem;

import java.util.List;

public record OrderRequest(
        int hall,
        int row,
        int seat,
        List<OrderItem> items
) {}
