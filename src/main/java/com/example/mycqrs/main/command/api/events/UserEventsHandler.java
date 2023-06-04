package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.data.repositories.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    private UserRepository userRepository;

    public UserEventsHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreateEvent event){
        User user =
                new User();
        BeanUtils.copyProperties(event,user);
        userRepository.save(user);
    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }
}
