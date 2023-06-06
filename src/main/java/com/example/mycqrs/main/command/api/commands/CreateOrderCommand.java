package com.example.mycqrs.main.command.api.commands;

import com.example.mycqrs.main.command.api.data.entities.Product;
import com.example.mycqrs.main.command.api.data.entities.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String trackingCode;
    private BigDecimal totalPrice;
    private User user;
    private Set<Product> products;
    private LocalDateTime date;
}
