package com.example.orderpractice.payment.serviceV2;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CARD")
@Transactional
@RequiredArgsConstructor

class CardPaymentService implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment init(Long orderId, int price) {
        var paymentInfo = new PaymentInfo("정훈", "하나은행", "1231");
        return paymentRepository.save(Payment.card(orderId, price, paymentInfo));
    }

    @Override
    public boolean pay(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
