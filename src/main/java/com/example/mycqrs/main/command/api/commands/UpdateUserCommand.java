package com.example.mycqrs.main.command.api.commands;

import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.Product;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Set;

@Data
@Builder
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String phone;
    private Set<Product> products;
    private Set<Order> order;

}
