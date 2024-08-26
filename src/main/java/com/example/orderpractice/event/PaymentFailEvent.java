package com.example.orderpractice.event;

public final class PaymentFailEvent extends PaymentEvent {

    PaymentFailEvent(Long orderId) {
        super(orderId);
    }
}
