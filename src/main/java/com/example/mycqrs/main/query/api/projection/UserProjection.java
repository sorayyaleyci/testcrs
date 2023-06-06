package com.example.mycqrs.main.query.api.projection;


import com.example.mycqrs.main.command.api.data.entities.User;
import com.example.mycqrs.main.command.api.data.services.UserService;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import com.example.mycqrs.main.query.api.queries.GetOrderQueryByUser;
import com.example.mycqrs.main.query.api.queries.GetUserQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProjection {
    @Autowired
    private UserService userService;

    @QueryHandler
    public List<UserRestModel> handle(GetUserQuery getUserQuery){
        List<User>users=
                userService.getUsers();
        List<UserRestModel> userRestModels=
                users.stream()
                .map(user -> UserRestModel
                        .builder()
                        .phone(user.getPhone())
                        .products(user.getProducts()) //issues found here
                        .order(user.getOrders())
                        .build())
                .collect(Collectors.toList());
        return userRestModels;
    }


    @QueryHandler
    public UserRestModel handle(GetOrderQueryByUser getOrderQueryByUser){
        User targetUser=userService.getUserByID(getOrderQueryByUser.getPhone());

        UserRestModel userRestModel=UserRestModel.builder()
                .phone(targetUser.getPhone())
                .order(targetUser.getOrders())
                .build();

        return userRestModel;
    }


}
