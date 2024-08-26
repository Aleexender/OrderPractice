package com.example.orderpractice.payment.domain;

import com.example.orderpractice.order.domain.BaseEntity;
import jakarta.persistence.Convert;
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

    @Convert(converter = PaymentStatusConverter.class)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Method paymentMethod;

    @Embedded
    private PaymentInfo paymentInfo;

    public static Payment card(Long orderId, int price, PaymentInfo paymentInfo) {
        return new Payment(orderId,price, new Requested(), Method.CARD, paymentInfo);
    }

    public static Payment cash(Long orderId, int price, PaymentInfo paymentInfo) {
        return new Payment(orderId, price, new Requested(), Method.CASH, paymentInfo);
    }

    public String getName() {
        return paymentInfo.getName();
    }

    public void payed(boolean success) {
        this.status = success ? status.success() : status.fail();
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
