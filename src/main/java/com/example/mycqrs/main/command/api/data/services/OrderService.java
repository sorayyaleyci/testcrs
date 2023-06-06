package com.example.mycqrs.main.command.api.data.services;

import com.example.mycqrs.main.command.api.commands.RemoveProductCommand;
import com.example.mycqrs.main.command.api.data.entities.Order;
import com.example.mycqrs.main.command.api.data.entities.Product;
import com.example.mycqrs.main.command.api.data.repositories.OrderRepository;
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
