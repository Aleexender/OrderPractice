package com.example.orderpractice.payment.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PaymentStatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        return attribute.name();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if (dbData.equalsIgnoreCase("FAILED")) {
            return new Failed();
        }

        if (dbData.equalsIgnoreCase("SUCCESS")) {
            return new Success();
        }

        return new Requested();
    }
}
