package ua.mykola.orderservice.mapper;

import ua.mykola.commons.model.Order;
import ua.mykola.orderservice.document.OrderDocument;
import ua.mykola.orderservice.rest.dto.OrderRequest;
import ua.mykola.orderservice.rest.dto.OrderResponse;


public class OrderMapper {

    public static OrderDocument toEntity(OrderRequest order) {
        return new OrderDocument(
                order.hall(),
                order.row(),
                order.seat(),
                order.items()
        );
    }

    public static Order toOrder(OrderDocument orderDocument) {
        return new Order(
                orderDocument.getId(),
                orderDocument.getHall(),
                orderDocument.getRow(),
                orderDocument.getSeat(),
                orderDocument.getItems()
        );
    }

    public static OrderResponse toOrderResponse(OrderDocument order) {
        return new OrderResponse(order.getId(),
                order.getHall(),
                order.getRow(),
                order.getSeat(),
                order.getCreatedAt(),
                order.getItems());
    }
}
