package com.example.orderpractice.payment.serviceV5;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CARD")
@Transactional
class CardPaymentService extends PaymentService {

    public CardPaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public Payment init(Long orderId, int price) {
        var paymentInfo = new PaymentInfo("정훈", "하나은행", "1231");
        return paymentRepository.save(Payment.card(orderId, price, paymentInfo));
    }

    @Override
    protected boolean invoke(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
