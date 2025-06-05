package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CartDetailsJpaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad CartDetails
@Repository
public interface CartDetailsJpaRepository extends JpaRepository<CartDetails, Long> {

}
