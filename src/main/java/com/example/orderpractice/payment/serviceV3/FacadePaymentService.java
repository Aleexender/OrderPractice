package com.example.orderpractice.payment.serviceV3;

import com.example.orderpractice.event.OrderEvent;
import com.example.orderpractice.event.PaymentFailEvent;
import com.example.orderpractice.event.PaymentSuccessEvent;
import com.example.orderpractice.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

//todo 4V payment init 을 숨겨버리고 스스로 리턴하게끔 구현하기
//todo 5 상태에 따라서 상태패턴을 적용시키기
@Service
@RequiredArgsConstructor
public class FacadePaymentService { // 우리 애플리케이션 내부에서 결제를 처리하는 퍼사드

    private final PaymentServiceFactory paymentServiceFactory;

    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener
    public void orderRequested(OrderEvent orderEvent) {
        var method = Payment.Method.valueOf(orderEvent.paymentMethod());

        var paymentService = paymentServiceFactory.get(method);

        var payment = paymentService.init(orderEvent.orderId(), orderEvent.price());

        var success = paymentService.pay(payment.getPaymentInfo(), orderEvent.price());

        var event = success
            ? new PaymentSuccessEvent(orderEvent.orderId(), payment.getName())
            : new PaymentFailEvent(orderEvent.orderId(), payment.getName());

        applicationEventPublisher.publishEvent(event);
    }
}
