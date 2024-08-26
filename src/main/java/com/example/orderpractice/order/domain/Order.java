package com.example.orderpractice.order.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public static Order makeOrder(List<OrderLine> orderLine ) {
        return new Order(OrderStatus.ORDER_REQUEST,orderLine);
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public enum OrderStatus {
        ORDER_REQUEST,
        PAYMENT_COMPLETE,
        SHIP_SUCCESS,
        ORDER_CANCEL;
    }
}
