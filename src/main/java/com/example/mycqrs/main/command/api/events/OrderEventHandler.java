package com.example.mycqrs.main.command.api.events;


import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.repositories.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreateEvent event){
        Order order =
                new Order();
        BeanUtils.copyProperties(event,order);
        orderRepository.save(order);
    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }
}
