package com.example.mycqrs.main.command.api.model;


import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class OrderRestModel {
    private String trackingCode;
    private BigDecimal totalPrice;
    private User user;
    private Set<Product> products;
    private LocalDateTime date;
}
