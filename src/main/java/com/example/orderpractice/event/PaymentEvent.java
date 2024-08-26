package com.example.orderpractice.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public sealed class PaymentEvent permits PaymentFailEvent, PaymentSuccessEvent {
    private final Long orderId;

    public static PaymentSuccessEvent success(Long orderId) {
        return new PaymentSuccessEvent(orderId);
    }

    public static PaymentFailEvent fail(Long orderId) {
        return new PaymentFailEvent(orderId);
    }
}
