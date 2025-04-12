package iuh.fit.product_service.services;

import iuh.fit.product_service.models.Product;
import iuh.fit.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(String id) {
        return repository.findById(id);
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Optional<Product> update(String id, Product updated) {
        return repository.findById(id).map(p -> {
            p.setName(updated.getName());
            p.setDescription(updated.getDescription());
            p.setPrice(updated.getPrice());
            p.setStock(updated.getStock());
            return repository.save(p);
        });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}