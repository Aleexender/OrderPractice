package com.example.orderpractice.payment.serviceV5;

import com.example.orderpractice.payment.domain.Payment;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentServiceFactory {
    private final Map<String, PaymentService> paymentServiceMap;
    private final DefaultPaymentService defaultPaymentService;

    public PaymentService get(String method) {
        var methods = Payment.Method.valueOf(method);

        return switch (methods) {
            case CARD, CASH, TRANSFER -> paymentServiceMap.get(methods.name());
        };
    }
}
