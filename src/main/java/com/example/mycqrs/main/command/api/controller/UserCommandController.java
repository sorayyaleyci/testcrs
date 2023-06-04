package com.example.mycqrs.main.command.api.controller;

import com.example.mycqrs.main.command.api.commands.CreateOrderCommand;
import com.example.mycqrs.main.command.api.commands.CreateUserCommand;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserCommandController {
    private CommandGateway commandGateway;

    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addUser(@RequestBody UserRestModel userRestModel){
        CreateUserCommand createUserCommand= CreateUserCommand.builder()
                .phone(userRestModel.getPhone())
                .products(userRestModel.getProducts())
                .build();
        return commandGateway.sendAndWait(createUserCommand);
    }
}
