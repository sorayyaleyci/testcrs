package com.example.mycqrs.main.command.api.data;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "karbar")
public class User {
    @Id
    private String phone;
    @OneToMany
    private Set<Product> products=new HashSet<>();
}
