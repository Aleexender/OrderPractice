package com.example.orderpractice.delivery;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    String zipCode;
    String streetName;
    String specificAddress;
}
