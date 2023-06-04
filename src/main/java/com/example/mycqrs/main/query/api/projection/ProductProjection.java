package com.example.mycqrs.main.query.api.projection;


import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.ProductRepository;
import com.example.mycqrs.main.command.api.model.ProductRestModel;
import com.example.mycqrs.main.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product>products=
                productRepository.findAll();
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
