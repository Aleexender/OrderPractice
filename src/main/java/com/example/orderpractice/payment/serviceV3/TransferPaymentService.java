package com.example.orderpractice.payment.serviceV3;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;

public class TransferPaymentService implements PaymentService {

    @Override
    public Payment init(Long orderId, int price) {
        return null;
    }

    @Override
    public boolean pay(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
