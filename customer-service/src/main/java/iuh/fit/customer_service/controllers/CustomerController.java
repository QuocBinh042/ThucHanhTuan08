package iuh.fit.customer_service.controllers;

import iuh.fit.customer_service.models.Customer;
import iuh.fit.customer_service.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable String id, @RequestBody Customer customer) {
        return service.update(id, customer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}