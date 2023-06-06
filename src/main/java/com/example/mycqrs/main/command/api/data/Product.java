package com.example.mycqrs.main.command.api.data;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    private String ProductId;
    private String name;
    private BigDecimal price;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "user_products")
    private User user;


}
