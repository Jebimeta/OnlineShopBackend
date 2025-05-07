package com.onlineshop.util;

import com.onlineshop.config.properties.RitaRougeProperties;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.TokenJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final RitaRougeProperties properties;

	private final TokenJpaRepository tokenRepository;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isValid(String token, UserDetails user) {
		return isTokenUsernameMatchingUser(token, user.getUsername()) && !isTokenExpired(token) && isTokenActive(token);
	}

	public boolean isValidRefreshToken(String token, Customer customer) {
		return isTokenUsernameMatchingUser(token, customer.getUsername()) && !isTokenExpired(token)
				&& isRefreshTokenActive(token);
	}

	private boolean isTokenUsernameMatchingUser(String token, String username) {
		return extractUsername(token).equals(username);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private boolean isTokenActive(String token) {
		return tokenRepository.findByAccessToken(token).map(t -> !t.isLoggedOut()).orElse(false);
	}

	private boolean isRefreshTokenActive(String token) {
		return tokenRepository.findByRefreshToken(token).map(t -> !t.isLoggedOut()).orElse(false);
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
	}

	public String generateAccessToken(Customer customer) {
		return generateToken(customer, properties.getSecurity().getJwt().getAccessTokenExpiration());
	}

	public String generateRefreshToken(Customer customer) {
		return generateToken(customer, properties.getSecurity().getJwt().getRefreshTokenExpiration());
	}

	private String generateToken(Customer customer, String expirationInMillis) {
		long expireTimeInMillis = Long.parseLong(expirationInMillis);
		Date expirationDate = Date.from(Instant.now().plus(expireTimeInMillis, ChronoUnit.MILLIS));

		return Jwts.builder()
			.subject(customer.getUsername())
			.claim("role", customer.getRol().name())
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(expirationDate)
			.signWith(getSigninKey())
			.compact();
	}

	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(properties.getSecurity().getJwt().getSecretKey());
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
