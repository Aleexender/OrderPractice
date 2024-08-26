package com.example.orderpractice.paymentv5.domain;

public class Success implements Status {
    private static final String ALREADY_PAYED = "이미 처리된 건입니다.";

    @Override
    public String name() {
        return "SUCCESS";
    }

    @Override
    public Status success() {
        throw new IllegalStateException(ALREADY_PAYED);
    }

    @Override
    public Status fail() {
        throw new IllegalStateException(ALREADY_PAYED);
    }
}
