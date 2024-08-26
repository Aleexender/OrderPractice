package com.example.orderpractice.paymentv5.service;

import com.example.orderpractice.event.OrderEvent;
import com.example.orderpractice.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

//todo 4V payment init 을 숨겨버리고 스스로 리턴하게끔 구현하기
//todo 5 상태에 따라서 상태패턴을 적용시키기, jpa에서는 그 상태를 불러올수 있을까?(유연하게) 2가지 방법이 존재
// todo
@Service
@RequiredArgsConstructor
public class FacadePaymentService { // 우리 애플리케이션 내부에서 결제를 처리하는 퍼사드

    private final PaymentServiceFactory paymentServiceFactory;
    private final ApplicationEventPublisher eventPublisher;

    @TransactionalEventListener
    public void orderRequested(OrderEvent orderEvent) {
        var method = Payment.Method.valueOf(orderEvent.paymentMethod());

        var paymentService = paymentServiceFactory.get(method);

        var paymentEvent = paymentService.pay(orderEvent.orderId(), orderEvent.price());

        eventPublisher.publishEvent(paymentEvent);
    }
}
