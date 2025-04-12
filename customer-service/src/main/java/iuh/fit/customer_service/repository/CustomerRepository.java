package iuh.fit.customer_service.repository;

import iuh.fit.customer_service.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}