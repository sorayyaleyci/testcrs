package com.example.mycqrs.main.command.api.data.services;

import com.example.mycqrs.main.command.api.commands.RemoveProductCommand;
import com.example.mycqrs.main.command.api.data.Order;
import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import com.example.mycqrs.main.command.api.data.repositories.OrderRepository;
import com.example.mycqrs.main.command.api.data.repositories.UserRepository;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderByID(String ID){

        Optional<Order> model=orderRepository.findById(ID);

        return model.orElse(null);
    }
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order addOrder(Order order){
        BigDecimal orderTotalPrice=BigDecimal.ZERO;
        Set<Product> productRestModels= order.getProducts();

        for (Product productModel : productRestModels) {
            RemoveProductCommand removeProductCommand= RemoveProductCommand.builder()
                    .stock(productModel.getStock())
                    .name(productModel.getName())
                    .price(productModel.getPrice())
                    .build();
            orderTotalPrice =orderTotalPrice.add(productModel.getPrice());
        }
        order.setTotalPrice(orderTotalPrice);
        order.setTrackingCode(UUID.randomUUID().toString());

        return orderRepository.save(order);
    }



}
