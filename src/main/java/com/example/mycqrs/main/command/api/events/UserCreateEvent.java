package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.entities.Order;
import com.example.mycqrs.main.command.api.data.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateEvent {
    private String userId;
    private String phone;
    private Set<Product> products;
    private Set<Order> orders;

}
