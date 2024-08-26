package com.example.orderpractice.payment.domain;

public interface Status {
    String name();

    Status success();

    Status fail();
}
