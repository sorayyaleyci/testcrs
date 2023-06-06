package com.example.mycqrs.main.query.api.controller;

import com.example.mycqrs.main.command.api.model.OrderRestModel;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import com.example.mycqrs.main.query.api.queries.GetOrderQuery;
import com.example.mycqrs.main.query.api.queries.GetOrderQueryByID;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(method = RequestMethod.GET,value="user/order/{ID}")
    public List<OrderRestModel> getUserOrderById(@PathVariable("ID") String orderId){

        GetOrderQueryByID getOrderQueryByID=
                new GetOrderQueryByID(orderId);
        List<OrderRestModel> orderRestModels =
                queryGateway.query(getOrderQueryByID,
                        ResponseTypes.multipleInstancesOf(OrderRestModel.class))
                        .join();

        return orderRestModels;
    }
}
