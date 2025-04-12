package iuh.fit.order_service.repository;

import iuh.fit.order_service.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}