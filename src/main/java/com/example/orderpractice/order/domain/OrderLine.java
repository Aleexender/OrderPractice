package com.example.orderpractice.order.domain;

import com.example.orderpractice.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_lines")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine extends BaseEntity {

    private String name;
    private BigDecimal price;
    private int quantity;

    public BigDecimal calculatePrice() {
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

}
