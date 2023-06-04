package com.example.mycqrs.main.query.api.controller;

import com.example.mycqrs.main.command.api.model.OrderRestModel;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.query.api.queries.GetOrderQuery;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {
    private QueryGateway queryGateway;

    public OrderQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @RequestMapping
    public List<OrderRestModel> getAllOrders(){
        GetOrderQuery getOrderQuery=
                new GetOrderQuery();
        List<OrderRestModel> orderRestModels =
                queryGateway.query(getOrderQuery,
                        ResponseTypes.multipleInstancesOf(OrderRestModel.class))
                        .join();

        return orderRestModels;
    }
}
