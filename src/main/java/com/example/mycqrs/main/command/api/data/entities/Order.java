package com.example.mycqrs.main.command.api.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Order {
    @Id
    private String orderId;
    private String trackingCode;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "karbar_orders")
    private User user;

    @OneToMany
    private Set<Product> products=new HashSet<>();
    private LocalDateTime date;
}
