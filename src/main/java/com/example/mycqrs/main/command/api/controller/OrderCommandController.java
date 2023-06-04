package com.example.mycqrs.main.command.api.controller;

import com.example.mycqrs.main.command.api.commands.CreateOrderCommand;
import com.example.mycqrs.main.command.api.commands.CreateProductCommand;
import com.example.mycqrs.main.command.api.model.OrderRestModel;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {
    private CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addOrder(@RequestBody OrderRestModel orderRestModel){
        CreateOrderCommand createOrderCommand= CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .date(orderRestModel.getDate())
                .products(orderRestModel.getProducts())
                .trackingCode(orderRestModel.getTrackingCode())
                .totalPrice(orderRestModel.getTotalPrice())
                .user(orderRestModel.getUser())
                .build();
        return commandGateway.sendAndWait(createOrderCommand);
    }
}
