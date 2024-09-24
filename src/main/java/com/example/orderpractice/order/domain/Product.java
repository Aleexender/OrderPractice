package com.example.orderpractice.order.domain;


import com.example.orderpractice.event.domain.ProductPriceChangeEvent;
import com.example.orderpractice.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private AtomicInteger stock;

    public void sell(int quantity) {
        if (this.stock.get() < quantity) {
            throw new IllegalStateException("재고가 부족합니다: ");
        }
        this.stock.set(this.stock.get() - quantity);
    }

    public void changePrice(BigDecimal newPrice) {
        if (!this.price.equals(newPrice)) {
            this.price = newPrice;
        }
    }
}
