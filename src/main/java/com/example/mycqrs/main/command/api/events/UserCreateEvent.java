package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
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
