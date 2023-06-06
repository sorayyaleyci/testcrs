package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.entities.Product;
import com.example.mycqrs.main.command.api.data.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateEvent {
    private String orderId;
    private String trackingCode;
    private BigDecimal totalPrice;
    private User user;
    private Set<Product> products;
    private LocalDateTime date;
}
