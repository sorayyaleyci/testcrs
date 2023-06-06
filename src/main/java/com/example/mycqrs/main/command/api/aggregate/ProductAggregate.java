package com.example.mycqrs.main.command.api.aggregate;

import com.example.mycqrs.main.command.api.commands.CreateProductCommand;
import com.example.mycqrs.main.command.api.commands.RemoveProductCommand;
import com.example.mycqrs.main.command.api.events.ProductCreateEvent;
import com.example.mycqrs.main.command.api.events.ProductRemoveEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String ProductId;
    private String name;
    private BigDecimal price;
    private Integer stock;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
        //validation
        ProductCreateEvent productCreateEvent =
                new ProductCreateEvent();
        BeanUtils.copyProperties(createProductCommand,productCreateEvent);
        AggregateLifecycle.apply(productCreateEvent);
    }
    @CommandHandler
    public ProductAggregate(RemoveProductCommand removeProductCommand){
        //validation
        ProductRemoveEvent productRemoveEvent =
                new ProductRemoveEvent();
        BeanUtils.copyProperties(removeProductCommand,productRemoveEvent);
        AggregateLifecycle.apply(productRemoveEvent);
    }
    public ProductAggregate(){}

    @EventSourcingHandler
    public void on(ProductCreateEvent productCreateEvent){
        this.stock=productCreateEvent.getStock();
        this.name=productCreateEvent.getName();
        this.ProductId=productCreateEvent.getProductId();
        this.price=productCreateEvent.getPrice();

    }
    @EventSourcingHandler
    public void on(ProductRemoveEvent productRemoveEvent){
        this.stock=productRemoveEvent.getStock();
        this.name=productRemoveEvent.getName();
        this.ProductId=productRemoveEvent.getProductId();
        this.price=productRemoveEvent.getPrice();

    }

}
