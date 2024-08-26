package com.example.orderpractice.order.service;

import com.example.orderpractice.event.OrderEvent;
import com.example.orderpractice.event.PaymentFailEvent;
import com.example.orderpractice.order.domain.Order;
import com.example.orderpractice.order.domain.OrderLine;
import com.example.orderpractice.order.dto.OrderRequest;
import com.example.orderpractice.order.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderRepository orderRepository;


    @Transactional
    public void register(List<OrderRequest> menus, String paymentMethod) {
        var orderLines = menus.stream().map(it -> new OrderLine(it.name(), it.quantity(), it.price())).toList();
        var totalPrice = menus.stream().mapToInt(OrderRequest::price).sum();
        var order = orderRepository.save(Order.makeOrder(orderLines));

        applicationEventPublisher.publishEvent(
            new OrderEvent(order.getId(), totalPrice, paymentMethod));
    }

    @TransactionalEventListener
    public void onPayFail(PaymentFailEvent event) {
        orderRepository.findById(event.orderId()).orElseThrow();
    }
}
