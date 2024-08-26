package com.example.orderpractice.paymentv5.domain;

public class Failed implements Status {
    private static final String ALREADY_FAILED = "이미 실패된 건입니다.";

    @Override
    public String name() {
        return "FAILED";
    }

    @Override
    public Status success() {
        throw new IllegalStateException(ALREADY_FAILED);
    }

    @Override
    public Status fail() {
        throw new IllegalStateException(ALREADY_FAILED);
    }
}
