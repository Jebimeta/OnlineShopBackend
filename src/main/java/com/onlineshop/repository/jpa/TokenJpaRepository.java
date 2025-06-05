package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// TokenJpaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Token
public interface TokenJpaRepository extends JpaRepository<Token, Long> {

	// Método para buscar todos los tokens de acceso activos de un usuario específico
	@Query("""
			    SELECT t
			    FROM Token t
			    INNER JOIN Customer c ON t.customer.id = c.id
			    WHERE t.customer.id = :customerId
			    AND t.loggedOut = false
			""")
	// Busca todos los tokens de acceso activos de un usuario por su ID
	List<Token> findAllAccessTokensByUser(@Param("customerId") Long customerId);

	// Método para buscar un token de acceso por su valor
	Optional<Token> findByAccessToken(String token);

	// Método para buscar un token de actualización por su valor
	Optional<Token> findByRefreshToken(String token);

}
