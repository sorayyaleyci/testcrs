package com.example.mycqrs.main.command.api.commands;

import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String phone;
    private Set<Product> products;
}
