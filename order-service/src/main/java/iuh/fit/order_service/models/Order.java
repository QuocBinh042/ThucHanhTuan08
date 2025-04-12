package iuh.fit.order_service.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String id;

    private String customerId;
    private List<String> productIds;
    private double totalPrice;
    private String status;
    private LocalDateTime createdAt;
}