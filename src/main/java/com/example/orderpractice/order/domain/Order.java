package com.example.orderpractice.order.domain;

import com.example.orderpractice.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public static Order makeOrder(List<OrderLine> orderLine) {
        return new Order(OrderStatus.ORDER_REQUEST, orderLine);
    }

    /* todo
        가격 테스트를 하기 위해서 List<OrderRequest>를 가져왔는데 이로 인해 dependency가 생김
        Request -> domain 로 역전? 된거같음 && order 가 List<OrderRequest>를 알아야할 책임이 있나? -> 아니라고 생각함
        그럼 어떻게 해결할수 있지???
        아직까진 모르겠다...
     */

    public enum OrderStatus {
        ORDER_REQUEST,
        PAYMENT_COMPLETE,
        SHIP_SUCCESS,
        ORDER_CANCEL;
    }
}
