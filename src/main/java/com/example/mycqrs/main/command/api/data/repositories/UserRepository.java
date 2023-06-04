package com.example.mycqrs.main.command.api.data.repositories;

import com.example.mycqrs.main.command.api.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
