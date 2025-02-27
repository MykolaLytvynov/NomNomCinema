package ua.mykola.orderservice.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.mykola.orderservice.rest.dto.OrderRequest;
import ua.mykola.orderservice.rest.dto.OrderResponse;
import ua.mykola.orderservice.rest.dto.PagedResponse;
import ua.mykola.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<OrderResponse>> getPosts(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "100") int size) {
        PagedResponse<OrderResponse> orders = orderService.findAll(size, page);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> post(@RequestBody OrderRequest orderRequest) {
        OrderResponse order = orderService.save(orderRequest);
        return ResponseEntity.ok(order);
    }
}
