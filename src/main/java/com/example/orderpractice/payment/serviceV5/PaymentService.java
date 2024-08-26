package com.example.orderpractice.payment.serviceV5;

import com.example.orderpractice.event.PaymentEvent;
import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
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
