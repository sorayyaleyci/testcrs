package com.example.mycqrs.main.command.api.data;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Order {
    @Id
    private String orderId;
    private String trackingCode;
    private BigDecimal totalPrice;
    @OneToOne
    private User user;
    @OneToMany
    private Set<Product> products=new HashSet<>();
    private Date date;
}
