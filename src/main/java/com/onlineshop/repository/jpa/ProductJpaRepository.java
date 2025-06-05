package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ProductJpaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Product
@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
