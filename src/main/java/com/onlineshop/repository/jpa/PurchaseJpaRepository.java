package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// PurchaseJpaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Purchase
@Repository
public interface PurchaseJpaRepository extends JpaRepository<Purchase, Long> {

}
