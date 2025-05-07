package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailsJpaRepository extends JpaRepository<CartDetails, Long> {

}
