package com.example.mycqrs.main.command.api.data.services;

import com.example.mycqrs.main.command.api.data.entities.Product;
import com.example.mycqrs.main.command.api.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductByID(String ID){

        Optional<Product> model=productRepository.findById(ID);

        return model.orElse(null);
    }
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }


}
