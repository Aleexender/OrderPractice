package com.example.orderpractice.event;

public record OrderEvent(
    Long orderId,
    int price,
    String paymentMethod
) {
}
