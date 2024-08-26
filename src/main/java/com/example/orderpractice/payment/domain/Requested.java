package com.example.orderpractice.payment.domain;

public class Requested implements Status {
    @Override
    public String name() {
        return "REQUESTED";
    }

    @Override
    public Status success() {
        return new Success();
    }

    @Override
    public Status fail() {
        return new Failed();
    }
}
