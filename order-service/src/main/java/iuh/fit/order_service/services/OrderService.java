package iuh.fit.order_service.services;

import iuh.fit.order_service.models.Order;
import iuh.fit.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    public List<Order> getAll() {
        return repository.findAll();
    }

    public Optional<Order> getById(String id) {
        return repository.findById(id);
    }

    public Order create(Order order) {
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        return repository.save(order);
    }

    public Optional<Order> updateStatus(String id, String status) {
        return repository.findById(id).map(o -> {
            o.setStatus(status);
            return repository.save(o);
        });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}