package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByUsername(String username);

	Optional<Customer> findByEmail(String email);

	Optional<Customer> findByVerificationToken(String verificationToken);

}
