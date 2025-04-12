package iuh.fit.order_service.models;

import lombok.Data;

@Data
public class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
}