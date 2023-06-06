package com.example.mycqrs.main.command.api.aggregate;


import com.example.mycqrs.main.command.api.commands.CreateOrderCommand;
import com.example.mycqrs.main.command.api.data.entities.Product;
import com.example.mycqrs.main.command.api.data.entities.User;
import com.example.mycqrs.main.command.api.events.OrderCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String trackingCode;
    private BigDecimal totalPrice;
    private User user;
    private Set<Product> products;
    private LocalDateTime date;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
        //validation
        OrderCreateEvent orderCreateEvent =
                new OrderCreateEvent();
        BeanUtils.copyProperties(createOrderCommand,orderCreateEvent);
        AggregateLifecycle.apply(orderCreateEvent);
    }
    public OrderAggregate(){}

    @EventSourcingHandler
    public void on(OrderCreateEvent orderCreateEvent){
        this.trackingCode=orderCreateEvent.getTrackingCode();
        this.date=orderCreateEvent.getDate();
        this.products=orderCreateEvent.getProducts();
        this.totalPrice=orderCreateEvent.getTotalPrice();
        this.user=orderCreateEvent.getUser();
        this.orderId=orderCreateEvent.getOrderId();

    }
}
