package com.example.mycqrs.main.command.api.model;

import com.example.mycqrs.main.command.api.data.Product;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserRestModel {
    private String phone;
    private Set<Product> products;
}
