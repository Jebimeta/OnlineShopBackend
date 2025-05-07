package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<Purchase, Long> {

}
