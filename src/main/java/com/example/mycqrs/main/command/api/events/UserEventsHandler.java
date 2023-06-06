package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.entities.User;
import com.example.mycqrs.main.command.api.data.services.ProductService;
import com.example.mycqrs.main.command.api.data.services.UserService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;


    @EventHandler
    public void on(UserCreateEvent event){
        User user =
                new User();
        BeanUtils.copyProperties(event,user);
        userService.addUser(user);
    }
    @EventHandler
    public void on(UserUpdateEvent event){
        if (productService.getProductByID(event.getUpdateId()).getStock()>=Integer.parseInt(event.getUpdateNum())){
        User user =
                new User();
        BeanUtils.copyProperties(event,user);
        userService.updateUser(user);}
        else {
            // no more product available
            System.out.println("out of stock Error");
            // user must be informed
        }
    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }

}
