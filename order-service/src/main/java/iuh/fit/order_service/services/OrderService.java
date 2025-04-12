package iuh.fit.order_service.services;

import iuh.fit.order_service.models.Customer;
import iuh.fit.order_service.models.Order;
import iuh.fit.order_service.models.Product;
import iuh.fit.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Optional<Order> getById(String id) {
        return repository.findById(id);
    }

    public Order create(Order order) {
        if (!isCustomerValid(order.getCustomerId())) {
            throw new RuntimeException("Khách hàng không tồn tại.");
        }

        List<Product> products = fetchProducts(order.getProductIds());
        double total = products.stream().mapToDouble(Product::getPrice).sum();

        order.setTotalPrice(total);
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

    public boolean isCustomerValid(String customerId) {
        try {
            restTemplate.getForEntity("http://localhost:8083/customers/" + customerId, Customer.class);
            return true;
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    public List<Product> fetchProducts(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String id : productIds) {
            try {
                Product product = restTemplate.getForObject("http://localhost:8081/products/" + id, Product.class);
                products.add(product);
            } catch (Exception e) {
                System.out.println("Không tìm thấy sản phẩm: " + id);
            }
        }
        return products;
    }
}