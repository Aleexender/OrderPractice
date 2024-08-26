package com.example.orderpractice.paymentv5.service;

import com.example.orderpractice.payment.domain.Payment;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentServiceFactory {
    private final Map<String, PaymentService> paymentServiceMap;

    public PaymentService get(Payment.Method method) {
        return switch (method) {
            case CARD, CASH, TRANSFER -> paymentServiceMap.get(method.name());
        };
    }
}
