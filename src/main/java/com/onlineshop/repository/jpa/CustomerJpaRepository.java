package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CustomerJpaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Customer
@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

	// Métodos para buscar un cliente por su nombre de usuario, correo electrónico o token de verificación
	Optional<Customer> findByUsername(String username);

	// Busca un cliente por su correo electrónico
	Optional<Customer> findByEmail(String email);

	// Busca un cliente por su token de verificación
	Optional<Customer> findByVerificationToken(String verificationToken);

}
