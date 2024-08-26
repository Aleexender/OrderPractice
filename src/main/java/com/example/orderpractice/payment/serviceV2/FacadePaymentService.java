package com.example.orderpractice.payment.serviceV2;

import com.example.orderpractice.event.OrderEvent;
import com.example.orderpractice.event.PaymentFailEvent;
import com.example.orderpractice.event.PaymentSuccessEvent;
import com.example.orderpractice.payment.domain.Payment;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

//todo v2 에서 치명적인 결함 찾기
//todo 2. 메소드에 의존한다-> PaymentService가 추가가 안되거나, Method가 없거나 이런 상황이면 어떻게 해야할까?
//todo 2.1 변화에 예민하지 못하다
@Service
@RequiredArgsConstructor
public class FacadePaymentService { // 우리 애플리케이션 내부에서 결제를 처리하는 퍼사드

    private final Map<String, PaymentService> paymentServiceMap;

    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener
    public void orderRequested(OrderEvent orderEvent) {
        Payment.Method method = Payment.Method.valueOf(orderEvent.paymentMethod());

        var paymentService = paymentServiceMap.get(method.name()); // get에서 해당하는 서비스가 구현이 안되어있으면?

        var payment = paymentService.init(orderEvent.orderId(), orderEvent.price());

        var success = paymentService.pay(payment.getPaymentInfo(), orderEvent.price());

        var event = success
            ? new PaymentSuccessEvent(orderEvent.orderId(), payment.getName())
            : new PaymentFailEvent(orderEvent.orderId(), payment.getName());

        applicationEventPublisher.publishEvent(event);
    }
}
