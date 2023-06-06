package com.example.mycqrs.main.command.api.events;


import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.repositories.OrderRepository;
import com.example.mycqrs.main.command.api.data.services.OrderService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    @Autowired
    private OrderService orderService;


    @EventHandler
    public void on(OrderCreateEvent event){
        Order order =
                new Order();
        BeanUtils.copyProperties(event,order);
        orderService.addOrder(order);
    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }
}
