package com.example.orderpractice.payment.serviceV4;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PaymentService {
    protected final PaymentRepository paymentRepository;

    protected abstract Payment init(Long orderId, int price);

    protected abstract boolean invoke(PaymentInfo paymentInfo, int price);

    public boolean pay(Long orderId, int price) {
        var payment = init(orderId, price);
        return invoke(payment.getPaymentInfo(), price);
    }
}
