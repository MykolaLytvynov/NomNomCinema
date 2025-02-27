package ua.mykola.orderservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.mykola.commons.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
public class OrderDocument {
    @Id
    private String id;
    private int hall;
    private int row;
    private int seat;
    private LocalDateTime createdAt;
    private List<OrderItem> items;

    public OrderDocument() {}

    public OrderDocument(int hall, int row, int seat, List<OrderItem> items) {
        this.id = UUID.randomUUID().toString();
        this.hall = hall;
        this.row = row;
        this.seat = seat;
        this.createdAt = LocalDateTime.now();
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public int getHall() {
        return hall;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
