package com.example.orderpractice.paymentv5.domain;

public interface Status {
    String name();

    Status success();

    Status fail();
}
