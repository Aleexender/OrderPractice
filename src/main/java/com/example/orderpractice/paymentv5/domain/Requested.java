package com.example.orderpractice.paymentv5.domain;

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
