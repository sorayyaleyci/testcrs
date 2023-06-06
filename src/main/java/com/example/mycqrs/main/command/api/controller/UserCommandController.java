package com.example.mycqrs.main.command.api.controller;

import com.example.mycqrs.main.command.api.commands.*;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.data.services.UserService;
import com.example.mycqrs.main.command.api.model.OrderRestModel;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserCommandController {
    private CommandGateway commandGateway;
    @Autowired
    private UserService userService;

    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addUser(@RequestBody UserRestModel userRestModel){
        CreateUserCommand createUserCommand= CreateUserCommand.builder()
                .phone(userRestModel.getPhone())
                //.products(userRestModel.getProducts())
                .build();
        return commandGateway.sendAndWait(createUserCommand);
    }
    @PostMapping("user/add_product")
    public String addUserProduct(@RequestBody UserRestModel userRestModel){
        UpdateUserCommand updateUserCommand= UpdateUserCommand.builder()
                .phone(userRestModel.getPhone())
                .products(userRestModel.getProducts())
                .build();
        return commandGateway.sendAndWait(updateUserCommand);
    }


        @PostMapping("/user/place_order")
        public String addUserOrder(@RequestBody UserRestModel userRestModel){

            CreateUserCommand createUserCommand=CreateUserCommand.builder()
                    .phone(userRestModel.getPhone())
                    .products(userRestModel.getProducts())
                    .build();

            List<ProductRestModel> productRestModels=
                    userRestModel.getProducts().stream().map(
                            product -> ProductRestModel
                            .builder()
                            .stock(product.getStock())
                            .name(product.getName())
                            .stock(product.getStock())
                            .build())
                    .collect(Collectors.toList());

            for (ProductRestModel productRestModel : productRestModels) {
                RemoveProductCommand removeProductCommand= RemoveProductCommand.builder()
                        .stock(productRestModel.getStock())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .build();
                commandGateway.sendAndWait(removeProductCommand);

            }
            commandGateway.sendAndWait(createUserCommand);
           // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String newId=UUID.randomUUID().toString();
            CreateOrderCommand createOrderCommand= CreateOrderCommand.builder()
                    .orderId(newId)
                    .date(now)
                    .products(userRestModel.getProducts())
                    .user(userService.getUserByID(userRestModel.getPhone()))
                    .build();
            commandGateway.sendAndWait(createOrderCommand);
            return "user/order/{newID}";
        }



}
