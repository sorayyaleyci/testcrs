package com.example.mycqrs.main.command.api.commands;

import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
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
