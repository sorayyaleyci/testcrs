package com.example.mycqrs.main;

import com.example.mycqrs.main.command.api.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    public void configure(EventProcessingConfigurer configurer){
        configurer.registerListenerInvocationErrorHandler(
                "product",
                configuration -> new ProductServiceEventsErrorHandler()
        );
    }
    
}
