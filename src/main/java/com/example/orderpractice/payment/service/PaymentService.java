package com.example.orderpractice.payment.service;

import com.example.orderpractice.event.OrderEvent;
import com.example.orderpractice.event.PaymentFailEvent;
import com.example.orderpractice.event.PaymentSuccessEvent;
import com.example.orderpractice.payment.domain.Payment;
import com.example.orderpractice.payment.domain.PaymentInfo;
import com.example.orderpractice.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CardPaymentService cardPaymentService;
    private final CashPaymentService cashPaymentService;

    @TransactionalEventListener
    public void orderRequested(OrderEvent orderEvent) {
        PaymentInfo cardPaymentInfo = new PaymentInfo("정훈", "하나은행", "1231");
        PaymentInfo cashPaymentInfo = new PaymentInfo("정훈", "none", null);

        var paymentMethod = Payment.Method.valueOf(orderEvent.paymentMethod());

        Payment payment = paymentMethod.isCard() ?
            Payment.card(orderEvent.orderId(), orderEvent.price(), cardPaymentInfo):
            Payment.cash(orderEvent.orderId(), orderEvent.price(), cashPaymentInfo);

        paymentRepository.save(payment);

        if (paymentMethod.isCard()) {
            boolean success = cardPaymentService.pay(cardPaymentInfo, orderEvent.price());
            var paymentEvent = getEvent(orderEvent, success, payment);
            applicationEventPublisher.publishEvent(paymentEvent);
        }else{
            boolean success = cashPaymentService.pay(cardPaymentInfo, orderEvent.price());
            var paymentEvent = getEvent(orderEvent, success, payment);
            applicationEventPublisher.publishEvent(paymentEvent);
        }
    }

    private Record getEvent(OrderEvent orderEvent, boolean success, Payment payment) {
        return success
            ? new PaymentSuccessEvent(orderEvent.orderId(), payment.getName())
            : new PaymentFailEvent(orderEvent.orderId(), payment.getName());
    }
}
