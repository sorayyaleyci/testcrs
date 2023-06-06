package com.example.mycqrs.main.command.api.data.services;

import com.example.mycqrs.main.command.api.data.entities.User;
import com.example.mycqrs.main.command.api.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByID(String ID){

        Optional<User> model=userRepository.findById(ID);

        return model.orElse(null);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        if(!userRepository.existsById(user.getPhone()))
             userRepository.save(user);
        return userRepository.getById(user.getPhone());
    }
    public User updateUser(User user){
        return    userRepository.save(user);

    }


}
