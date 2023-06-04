package com.example.mycqrs.main.query.api.projection;

import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.repositories.OrderRepository;
import com.example.mycqrs.main.command.api.model.OrderRestModel;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.query.api.queries.GetOrderQuery;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderProjection {
    private OrderRepository orderRepository;

    public OrderProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }
    @QueryHandler
    public List<OrderRestModel> handle(GetOrderQuery getOrderQuery){
        List<Order>orders=
                orderRepository.findAll();
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
}
