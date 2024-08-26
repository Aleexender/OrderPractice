package com.example.orderpractice.payment.serviceV3;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CASH")
@Transactional
@RequiredArgsConstructor
class CashPaymentService implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment init(Long orderId, int price) {
        var paymentInfo = new PaymentInfo("정훈", "none", null);
        return paymentRepository.save(Payment.cash(orderId, price, paymentInfo));
    }

    @Override
    public boolean pay(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
