package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.data.repositories.UserRepository;
import com.example.mycqrs.main.command.api.data.services.UserService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    @Autowired
    private UserService userService;


    @EventHandler
    public void on(UserCreateEvent event){
        User user =
                new User();
        BeanUtils.copyProperties(event,user);
        userService.addUser(user);
    }
    @EventHandler
    public void on(UserUpdateEvent event){
        User user =
                new User();
        BeanUtils.copyProperties(event,user);
        userService.updateUser(user);
    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }

}
