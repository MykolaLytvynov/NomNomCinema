package ua.mykola.commons.model;

import java.util.List;

public record Order(
        String orderId,
        int hall,
        int row,
        int seat,
        List<OrderItem> items
) {}
