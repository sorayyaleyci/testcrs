package com.example.mycqrs.main.query.api.projection;

import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.data.repositories.OrderRepository;
import com.example.mycqrs.main.command.api.data.services.OrderService;
import com.example.mycqrs.main.command.api.model.OrderRestModel;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.command.api.model.UserRestModel;
import com.example.mycqrs.main.query.api.queries.GetOrderQuery;
import com.example.mycqrs.main.query.api.queries.GetOrderQueryByID;
import com.example.mycqrs.main.query.api.queries.GetOrderQueryByUser;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderProjection {
    @Autowired
    private OrderService orderService;


    @QueryHandler
    public List<OrderRestModel> handle(GetOrderQuery getOrderQuery){
        List<Order>orders=orderService.getOrders();
        List<OrderRestModel> orderRestModel=
                orders.stream()
                        .map(order -> OrderRestModel
                                .builder()
                                .products(order.getProducts())
                                .totalPrice(order.getTotalPrice())
                                .date(order.getDate())
                                .trackingCode(order.getTrackingCode())
                                .user(order.getUser())
                                .build())
                        .collect(Collectors.toList());
        return orderRestModel;
    }
    @QueryHandler
    public OrderRestModel handle(GetOrderQueryByID getOrderQueryByID){
        Order targetOrder=orderService.getOrderByID(getOrderQueryByID.getOrderId());

        OrderRestModel orderRestModel=OrderRestModel.builder()
                .products(targetOrder.getProducts())
                .trackingCode(targetOrder.getTrackingCode())
                .user(targetOrder.getUser())
                .date(targetOrder.getDate())
                .totalPrice(targetOrder.getTotalPrice())
                .build();

        return orderRestModel;
    }


}
