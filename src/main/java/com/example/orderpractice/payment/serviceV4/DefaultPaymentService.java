package com.example.orderpractice.payment.serviceV4;

import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service("DEFAULT")
public class DefaultPaymentService extends PaymentService {

    public DefaultPaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public Payment init(Long orderId, int price) {
        return null;
    }

    @Override
    protected boolean invoke(PaymentInfo paymentInfo, int price) {
        return false;
    }
}
