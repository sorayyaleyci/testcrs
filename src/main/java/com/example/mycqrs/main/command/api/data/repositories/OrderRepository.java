package com.example.mycqrs.main.command.api.data.repositories;

import com.example.mycqrs.main.command.api.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
}
