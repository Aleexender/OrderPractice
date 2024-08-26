package com.example.orderpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OrderPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPracticeApplication.class, args);
    }

}
