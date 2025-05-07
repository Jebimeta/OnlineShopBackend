package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {

	@Query("""
			    SELECT t
			    FROM Token t
			    INNER JOIN Customer c ON t.customer.id = c.id
			    WHERE t.customer.id = :customerId
			    AND t.loggedOut = false
			""")
	List<Token> findAllAccessTokensByUser(@Param("customerId") Long customerId);

	Optional<Token> findByAccessToken(String token);

	Optional<Token> findByRefreshToken(String token);

}
