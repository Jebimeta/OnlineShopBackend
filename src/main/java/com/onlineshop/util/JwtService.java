package com.onlineshop.util;

import com.onlineshop.config.properties.OnlineShopProperties;
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

// Service para manejar la generación y validación de tokens JWT
@Service
@RequiredArgsConstructor
public class JwtService {

	private final OnlineShopProperties properties;

	private final TokenJpaRepository tokenRepository;

	/**
	 * Extrae el nombre de usuario del token JWT.
	 *
	 * @param token El token JWT del cual se extraerá el nombre de usuario.
	 * @return El nombre de usuario extraído del token.
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Verifica si el token es válido para un usuario específico.
	 *
	 * @param token El token JWT a validar.
	 * @param user  Los detalles del usuario para comparar con el token.
	 * @return true si el token es válido, false en caso contrario.
	 */
	public boolean isValid(String token, UserDetails user) {
		return isTokenUsernameMatchingUser(token, user.getUsername()) && !isTokenExpired(token) && isTokenActive(token);
	}

	/**
	 * Verifica si el token de actualización es válido para un cliente específico.
	 *
	 * @param token    El token JWT de actualización a validar.
	 * @param customer El cliente para comparar con el token.
	 * @return true si el token de actualización es válido, false en caso contrario.
	 */
	public boolean isValidRefreshToken(String token, Customer customer) {
		return isTokenUsernameMatchingUser(token, customer.getUsername()) && !isTokenExpired(token)
				&& isRefreshTokenActive(token);
	}

	/**
	 * Verifica si el token es válido para un cliente específico.
	 *
	 * @param token    El token JWT a validar.
	 * @param customer El cliente para comparar con el token.
	 * @return true si el token es válido, false en caso contrario.
	 */
	private boolean isTokenUsernameMatchingUser(String token, String username) {
		return extractUsername(token).equals(username);
	}

	/**
	 * Verifica si el token ha expirado.
	 *
	 * @param token El token JWT a verificar.
	 * @return true si el token ha expirado, false en caso contrario.
	 */
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Verifica si el token está activo.
	 *
	 * @param token El token JWT a verificar.
	 * @return true si el token está activo, false en caso contrario.
	 */
	private boolean isTokenActive(String token) {
		return tokenRepository.findByAccessToken(token).map(t -> !t.isLoggedOut()).orElse(false);
	}

	/**
	 * Verifica si el token de actualización está activo.
	 *
	 * @param token El token JWT de actualización a verificar.
	 * @return true si el token de actualización está activo, false en caso contrario.
	 */
	private boolean isRefreshTokenActive(String token) {
		return tokenRepository.findByRefreshToken(token).map(t -> !t.isLoggedOut()).orElse(false);
	}

	/**
	 * Extrae la fecha de expiración del token JWT.
	 *
	 * @param token El token JWT del cual se extraerá la fecha de expiración.
	 * @return La fecha de expiración del token.
	 */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

/**
	 * Extrae un reclamo específico del token JWT utilizando un resolvedor de funciones.
	 *
	 * @param token    El token JWT del cual se extraerá el reclamo.
	 * @param resolver La función que define cómo extraer el reclamo.
	 * @param <T>      El tipo del reclamo a extraer.
	 * @return El valor del reclamo extraído.
	 */
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	/**
	 * Extrae todos los reclamos del token JWT.
	 *
	 * @param token El token JWT del cual se extraerán todos los reclamos.
	 * @return Un objeto Claims que contiene todos los reclamos del token.
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
	}

	/**
	 * Genera un token de acceso JWT para un cliente específico.
	 *
	 * @param customer El cliente para el cual se generará el token de acceso.
	 * @return El token de acceso JWT generado.
	 */
	public String generateAccessToken(Customer customer) {
		return generateToken(customer, properties.getSecurity().getJwt().getAccessTokenExpiration());
	}

	/**
	 * Genera un token de actualización JWT para un cliente específico.
	 *
	 * @param customer El cliente para el cual se generará el token de actualización.
	 * @return El token de actualización JWT generado.
	 */
	public String generateRefreshToken(Customer customer) {
		return generateToken(customer, properties.getSecurity().getJwt().getRefreshTokenExpiration());
	}

	/**
	 * Genera un token JWT para un cliente específico con una duración específica.
	 *
	 * @param customer           El cliente para el cual se generará el token.
	 * @param expirationInMillis La duración del token en milisegundos.
	 * @return El token JWT generado.
	 */
	private String generateToken(Customer customer, String expirationInMillis) {
		long expireTimeInMillis = Long.parseLong(expirationInMillis);
		Date expirationDate = Date.from(Instant.now().plus(expireTimeInMillis, ChronoUnit.MILLIS));

		// Construye el token JWT con los detalles del cliente y la fecha de expiración
		return Jwts.builder()
			.subject(customer.getUsername()) // Establece el nombre de usuario como sujeto del token
			.claim("role", customer.getRol().name()) // Agrega el rol del cliente como un reclamo
			.issuedAt(new Date(System.currentTimeMillis())) // Establece la fecha de emisión del token
			.expiration(expirationDate) // Establece la fecha de expiración del token
			.signWith(getSigninKey()) // Firma el token con la clave secreta
			.compact(); // Devuelve el token JWT generado
	}

	/**
	 * Obtiene la clave secreta utilizada para firmar los tokens JWT.
	 *
	 * @return La clave secreta como un objeto SecretKey.
	 */
	private SecretKey getSigninKey() {
		// Decodifica la clave secreta desde una cadena Base64 URL y la convierte en un objeto SecretKey
		byte[] keyBytes = Decoders.BASE64URL.decode(properties.getSecurity().getJwt().getSecretKey());
		// Utiliza la clave decodificada para crear un SecretKey que se usará para firmar los tokens
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
