package com.example.orderpractice.payment.domain;

import com.example.orderpractice.order.domain.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
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
    private Payment.Status status;

    @Transient
    private com.example.orderpractice.payment.domain.Status state;

    @Enumerated(EnumType.STRING)
    private Method paymentMethod;

    @Embedded
    private PaymentInfo paymentInfo;

    public static Payment card(Long orderId, int price, PaymentInfo paymentInfo) {
        return new Payment(orderId,price, Status.REQUESTED,new Requested(), Method.CARD, paymentInfo);
    }

    public static Payment cash(Long orderId, int price, PaymentInfo paymentInfo) {
        return new Payment(orderId, price, Status.REQUESTED, new Requested(), Method.CASH, paymentInfo);
    }

    public String getName() {
        return paymentInfo.getName();
    }

    public void payed(boolean success) {
        if (state == null) {
            state = switch (this.status) {
                case FAILED -> new Failed();
                case SUCCESS -> new Success();
                case REQUESTED -> new Requested();
            };
        }

        this.state = success ? state.success() : state.fail();
    }

    public enum Status {
        FAILED,
        REQUESTED,
        SUCCESS,
        ;
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
