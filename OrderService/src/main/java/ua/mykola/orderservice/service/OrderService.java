package ua.mykola.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.mykola.commons.model.Order;
import ua.mykola.orderservice.document.OrderDocument;
import ua.mykola.orderservice.mapper.OrderMapper;
import ua.mykola.orderservice.repository.OrderRepository;
import ua.mykola.orderservice.rest.dto.OrderRequest;
import ua.mykola.orderservice.rest.dto.OrderResponse;
import ua.mykola.orderservice.rest.dto.PagedResponse;

@Service
public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderSender orderSender;

    public OrderService(OrderRepository orderRepository, OrderSender orderSender) {
        this.orderRepository = orderRepository;
        this.orderSender = orderSender;
    }

    public OrderResponse save(OrderRequest request) {
        LOG.info("Received order request: {}", request);
        OrderDocument orderDocument = OrderMapper.toEntity(request);

        orderRepository.save(orderDocument);
        LOG.info("Order saved to database: {}", orderDocument);

        Order order = OrderMapper.toOrder(orderDocument);
        orderSender.send(order);

        return OrderMapper.toOrderResponse(orderDocument);
    }

    public PagedResponse<OrderResponse> findAll(int size, int page) {
        Page<OrderResponse> orderPage = orderRepository
                .findAll(PageRequest.of(page, size, Sort.by("createdAt").descending()))
                .map(OrderMapper::toOrderResponse);
        return PagedResponse.fromPage(orderPage);
    }
}
