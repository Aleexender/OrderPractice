package com.example.orderpractice.order.service;

import com.example.orderpractice.order.domain.OrderLine;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
 class OrderCalculationService {

    public int getPriceAmount(List<OrderLine> orderLines) {
        return orderLines.stream()
            .map(OrderLine::calculatePrice)
            .mapToInt(BigDecimal::intValue)
            .sum();
    }

}