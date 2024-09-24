package com.example.orderpractice.order.service;

import com.example.orderpractice.event.ProductEvent;
import com.example.orderpractice.event.PriceChangeEvent;
import com.example.orderpractice.order.domain.OrderLine;
import com.example.orderpractice.order.dto.OrderRequest;
import com.example.orderpractice.order.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ProductManagingService productManagingService;

    @Transactional
    public void place(List<OrderRequest> menus, String paymentMethod) {
        var orderLines = menus.stream().map(menu -> {
            var product = productRepository.findByNameWithLock(menu.name()).orElseThrow(
                () -> new IllegalArgumentException("상품이 존재하지 않습니다: " + menu.name()));

            productManagingService.sell(product, menu.quantity());

            return new OrderLine(menu.name(), product.getPrice(), menu.quantity());
        }).collect(Collectors.toList());


        applicationEventPublisher.publishEvent(new ProductEvent(orderLines, paymentMethod));
    }


    @Transactional
    public void changePrice(Long id, BigDecimal newPrice) {
        var product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        product.changePrice(newPrice);

        applicationEventPublisher.publishEvent(new PriceChangeEvent(product.getName(),
            product.getId(), product.getPrice()));
    }
}
