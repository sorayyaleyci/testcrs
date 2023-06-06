package com.example.mycqrs.main.query.api.queries;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderQueryByID {
    private String orderId;

    public GetOrderQueryByID(String orderId) {
        this.orderId = orderId;
    }
}
