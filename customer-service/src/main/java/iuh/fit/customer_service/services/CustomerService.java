package iuh.fit.customer_service.services;

import iuh.fit.customer_service.models.Customer;
import iuh.fit.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Optional<Customer> getById(String id) {
        return repository.findById(id);
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> update(String id, Customer updated) {
        return repository.findById(id).map(c -> {
            c.setName(updated.getName());
            c.setEmail(updated.getEmail());
            c.setPhone(updated.getPhone());
            c.setAddress(updated.getAddress());
            return repository.save(c);
        });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}