package com.example.orderpractice.payment.service;

import com.example.orderpractice.payment.domain.PaymentInfo;
import org.springframework.stereotype.Service;

@Service
public class HanaPaymentServiceImpl implements CardPaymentService{
    @Override
    public boolean pay(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
