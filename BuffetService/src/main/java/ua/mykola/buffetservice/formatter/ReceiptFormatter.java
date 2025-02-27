package ua.mykola.buffetservice.formatter;

import org.springframework.stereotype.Component;
import ua.mykola.commons.model.Order;
import ua.mykola.commons.model.OrderItem;

@Component
public class ReceiptFormatter {

    public String format(Order order) {
        return "\n" +
                "================================\n" +
                "          ORDER DETAILS         \n" +
                "================================\n" +
                " Order ID: " + order.orderId() + "\n\n" +
                " ITEMS TO PREPARE:\n" + formatItems(order) +
                "--------------------------------\n" +
                " DELIVERY INFO FOR WAITER:\n" +
                "  → Hall: " + order.hall() + "\n" +
                "  → Row: " + order.row() + "\n" +
                "  → Seat: " + order.seat() + "\n" +
                "================================";
    }

    private String formatItems(Order order) {
        StringBuilder sb = new StringBuilder();
        for (OrderItem item : order.items()) {
            sb.append("  - ")
                    .append(item.itemName())
                    .append(" x ")
                    .append(item.quantity())
                    .append("\n");
        }
        return sb.toString();
    }
}
