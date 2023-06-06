package com.example.mycqrs.main.query.api.projection;


import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.repositories.ProductRepository;
import com.example.mycqrs.main.command.api.data.services.ProductService;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    @Autowired
    private ProductService productService;


    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product>products=
                productService.getProducts();
        List<ProductRestModel> productRestModels=
                products.stream()
                .map(product -> ProductRestModel
                        .builder()
                        .price(product.getPrice())
                        .name(product.getName())
                        .stock(product.getStock())
                        .build())
                .collect(Collectors.toList());
        return productRestModels;
    }
}
