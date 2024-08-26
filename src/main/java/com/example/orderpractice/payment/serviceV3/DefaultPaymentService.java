package com.example.orderpractice.payment.serviceV3;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import org.springframework.stereotype.Service;

@Service("DEFAULT")
public class DefaultPaymentService implements PaymentService{
    @Override
    public Payment init(Long orderId, int price) {
        return null;
    }

    @Override
    public boolean pay(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
