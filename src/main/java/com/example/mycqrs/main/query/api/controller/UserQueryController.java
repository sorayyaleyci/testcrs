package com.example.mycqrs.main.query.api.controller;


import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import com.example.mycqrs.main.query.api.queries.GetUserQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserQueryController {
    private QueryGateway queryGateway;

    public UserQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @RequestMapping
    public List<UserRestModel> getAllUsers(){
        GetUserQuery getUserQuery=
                new GetUserQuery();
        List<UserRestModel> userRestModels =
        queryGateway.query(getUserQuery,
                ResponseTypes.multipleInstancesOf(UserRestModel.class))
                .join();

        return userRestModels;
    }
}
