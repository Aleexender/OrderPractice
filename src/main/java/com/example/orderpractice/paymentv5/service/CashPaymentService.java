package com.example.orderpractice.paymentv5.service;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CASH")
@Transactional
class CashPaymentService extends PaymentService {

    public CashPaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public Payment init(Long orderId, int price) {
        var paymentInfo = new PaymentInfo("정훈", "none", null);
        return paymentRepository.save(Payment.cash(orderId, price, paymentInfo));
    }

    @Override
    protected boolean invoke(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
