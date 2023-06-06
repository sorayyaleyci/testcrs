package com.example.mycqrs.main.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRemoveEvent {
    private String ProductId;

    private String name;
    private BigDecimal price;
    private Integer stock;
}
