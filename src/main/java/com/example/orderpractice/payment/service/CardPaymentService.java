package com.example.orderpractice.payment.service;

import com.example.orderpractice.payment.domain.PaymentInfo;

public interface CardPaymentService {
    boolean pay(PaymentInfo paymentInfo, int price);
}
