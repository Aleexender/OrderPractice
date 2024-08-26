package com.example.orderpractice.payment.serviceV3;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;

public interface PaymentService {

    Payment init(Long orderId, int price);

    boolean pay(PaymentInfo paymentInfo, int price);
}
