package com.example.mycqrs.main.command.api.aggregate;


import com.example.mycqrs.main.command.api.commands.CreateOrderCommand;
import com.example.mycqrs.main.command.api.commands.CreateUserCommand;
import com.example.mycqrs.main.command.api.commands.UpdateUserCommand;
import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.events.OrderCreateEvent;
import com.example.mycqrs.main.command.api.events.UserCreateEvent;
import com.example.mycqrs.main.command.api.events.UserUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String phone;
    private Set<Product> products;
    private Set<Order> orders;


    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        //validation
        UserCreateEvent userCreateEvent =
                new UserCreateEvent();
        BeanUtils.copyProperties(createUserCommand,userCreateEvent);
        AggregateLifecycle.apply(userCreateEvent);
    }
    @CommandHandler
    public UserAggregate(UpdateUserCommand updateUserCommand){
        //validation
        UserUpdateEvent userUpdateEvent =
                new UserUpdateEvent();
        BeanUtils.copyProperties(updateUserCommand,userUpdateEvent);
        AggregateLifecycle.apply(userUpdateEvent);
    }
    public UserAggregate(){}

    @EventSourcingHandler
    public void on(UserCreateEvent userCreateEvent){
        this.products=userCreateEvent.getProducts();
        this.phone= userCreateEvent.getPhone();
        this.orders=userCreateEvent.getOrders();

    }
    @EventSourcingHandler
    public void on(UserUpdateEvent userUpdateEvent){
        this.products=userUpdateEvent.getProducts();
        this.phone= userUpdateEvent.getPhone();
        this.orders=userUpdateEvent.getOrders();

    }
}
