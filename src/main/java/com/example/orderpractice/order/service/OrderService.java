package com.example.orderpractice.order.service;

import com.example.orderpractice.event.OrderEvent;
import com.example.orderpractice.event.PaymentFailEvent;
import com.example.orderpractice.event.ProductEvent;
import com.example.orderpractice.order.domain.Order;
import com.example.orderpractice.order.domain.OrderLine;
import com.example.orderpractice.order.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ApplicationEventPublisher applicationEventPublisher;

    private final OrderRepository orderRepository;

    private final OrderCalculationService calculationService;

    @TransactionalEventListener
    public void place(ProductEvent productEvent) {
        var order = orderRepository.save(Order.makeOrder(productEvent.orderLines()));
        var totalPrice = calculationService.getPriceAmount(productEvent.orderLines());
        List<String> names = productEvent.orderLines().stream().map(OrderLine::getName).toList();

        applicationEventPublisher.publishEvent(
            new OrderEvent(order.getId(), totalPrice, productEvent.paymentMethod(), names));
    }

    @TransactionalEventListener
    public void onPayFail(PaymentFailEvent event) {
        orderRepository.findById(event.getOrderId()).orElseThrow();
    }
}
