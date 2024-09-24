package com.example.orderpractice.order.service;

import com.example.orderpractice.order.domain.Product;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
 class ProductManagingService {
    private final BlockingQueue<Boolean> blockingQueue = new LinkedBlockingQueue<>(2);

    public void sell(Product product, int quantity) {
        try {
            var flag = blockingQueue.poll(300, TimeUnit.MICROSECONDS);
            if (flag == null) {
                throw new RuntimeException();
            }

            product.sell(quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void add(int q) {
        for (int i = 0; i < q; i++) {
            blockingQueue.add(true);
        }
    }
}
