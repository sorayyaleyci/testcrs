package com.example.mycqrs.main.command.api.events;

import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.repositories.ProductRepository;
import com.example.mycqrs.main.command.api.data.services.ProductService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    @Autowired
    private ProductService productService;



    @EventHandler
    public void on(ProductCreateEvent event){
        Product product =
                new Product();
        BeanUtils.copyProperties(event,product);
        productService.addProduct(product);
    }
    @EventHandler
    public void on(ProductRemoveEvent event){
        Product product =
                new Product();
        if (product.getStock()>=1)product.setStock(product.getStock()-1);
        BeanUtils.copyProperties(event,product);
        productService.addProduct(product);

    }
    @EventHandler
    public void handle(Exception e)throws Exception{
        throw e;
    }
}
