package com.example.orderpractice.order.dto;

import java.awt.Menu;
import java.util.List;

public record OrderRequest(
    String name,
    int quantity,
    int price
) {
}

