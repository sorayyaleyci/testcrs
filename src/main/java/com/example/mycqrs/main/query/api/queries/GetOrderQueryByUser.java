package com.example.mycqrs.main.query.api.queries;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderQueryByUser {
    private String phone;
    public GetOrderQueryByUser(String phone) {
        this.phone=phone;
    }

}
