package com.example.mycqrs.main.command.api.aggregate;


import com.example.mycqrs.main.command.api.commands.CreateOrderCommand;
import com.example.mycqrs.main.command.api.commands.CreateProductCommand;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.events.OrderCreateEvent;
import com.example.mycqrs.main.command.api.events.ProductCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String trackingCode;
    private BigDecimal totalPrice;
    private User user;
    private Set<Product> products;
    private Date date;

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
