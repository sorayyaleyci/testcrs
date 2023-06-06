package com.example.mycqrs.main.query.api.queries;


import com.example.mycqrs.main.command.api.data.Product;
import com.example.mycqrs.main.command.api.data.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.data.web.ProjectedPayload;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class GetOrderQueryByUser {
    private String phone;
    public GetOrderQueryByUser(String phone) {
        this.phone=phone;
    }

}
