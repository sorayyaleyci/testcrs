package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.ProductRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreateEvent event){
        Product product =
                new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product);
    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }
}
