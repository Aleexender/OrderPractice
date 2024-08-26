package com.example.orderpractice.paymentv5.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {
    private String paymentName;

    private String bankName;

    private String cardNumber;


    public String getName() {
        return this.paymentName;
    }





}
