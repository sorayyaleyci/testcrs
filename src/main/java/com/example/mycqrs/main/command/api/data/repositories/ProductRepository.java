package com.example.mycqrs.main.command.api.data.repositories;

import com.example.mycqrs.main.command.api.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
