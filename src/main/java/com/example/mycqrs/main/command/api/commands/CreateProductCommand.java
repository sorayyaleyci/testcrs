package com.example.mycqrs.main.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String ProductId;
    private String name;
    private BigDecimal price;
    private Integer stock;
}
