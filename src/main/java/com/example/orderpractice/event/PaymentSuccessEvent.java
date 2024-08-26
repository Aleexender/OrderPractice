package com.example.orderpractice.event;

public final class PaymentSuccessEvent extends PaymentEvent {

    PaymentSuccessEvent(Long orderId) {
        super(orderId);
    }
}
