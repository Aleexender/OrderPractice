package com.example.orderpractice.paymentv5.service;

import com.example.orderpractice.event.PaymentEvent;
import com.example.orderpractice.payment.repository.PaymentRepository;
import com.example.orderpractice.paymentv5.domain.Payment;
import com.example.orderpractice.paymentv5.domain.PaymentInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PaymentService {
    protected final PaymentRepository paymentRepository;

    protected abstract Payment init(Long orderId, int price);

    protected abstract boolean invoke(PaymentInfo paymentInfo, int price);

    public PaymentEvent pay(Long orderId, int price) {
        var payment = init(orderId, price);

        var success = invoke(payment.getPaymentInfo(), price);

        payment.payed(success);

        return success ? PaymentEvent.success(orderId) : PaymentEvent.fail(orderId);
    }
}
