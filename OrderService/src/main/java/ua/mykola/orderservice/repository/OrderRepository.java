package ua.mykola.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.mykola.orderservice.document.OrderDocument;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, String> {
}
