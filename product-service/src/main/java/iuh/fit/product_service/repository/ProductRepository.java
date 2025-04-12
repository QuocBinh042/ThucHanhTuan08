package iuh.fit.product_service.repository;

import iuh.fit.product_service.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}