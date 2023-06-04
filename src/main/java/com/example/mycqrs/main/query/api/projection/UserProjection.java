package com.example.mycqrs.main.query.api.projection;


import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.data.repositories.ProductRepository;
import com.example.mycqrs.main.command.api.data.repositories.UserRepository;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import com.example.mycqrs.main.query.api.queries.GetUserQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProjection {
    private UserRepository userRepository;

    public UserProjection(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @QueryHandler
    public List<UserRestModel> handle(GetUserQuery getUserQuery){
        List<User>users=
                userRepository.findAll();
        List<UserRestModel> userRestModels=
                users.stream()
                .map(user -> UserRestModel
                        .builder()
                        .phone(user.getPhone())
                     //   .products(user.getProducts()) //issues here
                        .build())
                .collect(Collectors.toList());
        return userRestModels;
    }
}
