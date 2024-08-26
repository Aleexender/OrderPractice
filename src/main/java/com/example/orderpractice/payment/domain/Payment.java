package com.example.orderpractice.payment.domain;

import com.example.orderpractice.order.domain.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {
    private Long orderId;

    private int price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Method paymentMethod;

    @Embedded
    private PaymentInfo paymentInfo;

    public static Payment card(Long orderId, int price, PaymentInfo paymentInfo) {
        return new Payment(orderId,price,Status.PAYMENT_REQUESTED, Method.CARD, paymentInfo);
    }

    public static Payment cash(Long orderId, int price, PaymentInfo paymentInfo) {
        return new Payment(orderId, price, Status.PAYMENT_REQUESTED, Method.CASH, paymentInfo);
    }

    public String getName() {
        return paymentInfo.getName();
    }

    public enum Status {
        PAYMENT_REQUESTED,
        PAYMENT_COMPLETE;
    }

    public enum Method {
        CARD,
        CASH,
        TRANSFER
        ;

        public boolean isCard() {
            return this == CARD;
        }
    }
}
