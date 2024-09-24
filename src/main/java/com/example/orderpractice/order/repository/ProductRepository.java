package com.example.orderpractice.order.repository;

import com.example.orderpractice.order.domain.Product;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "500")})
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findByNameWithLock(String name);

}
