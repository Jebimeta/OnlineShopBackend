package com.onlineshop.service.auth;

import com.onlineshop.domain.vo.AuthenticationResponse;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.entities.Token;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.repository.jpa.TokenJpaRepository;
import com.onlineshop.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Clase que maneja la lógica de autenticación y generación de tokens para los clientes
@Service
@RequiredArgsConstructor
public class CustomerTokenService {

	private final TokenJpaRepository tokenRepository;

	private final JwtService jwtService;

	private final CustomerJpaRepository customerRepository;

	/**
	 * Revoca todos los tokens de acceso de un usuario específico. Esta operación marca
	 * todos los tokens como "logged out" (cerrados sesión).
	 * @param user El usuario del cual se revocarán los tokens. Esto es útil paracerrar
	 * sesión de manera efectiva y asegurar que no se puedan usar tokens antiguos.
	 */
	public void revokeAllTokenByUser(Customer user) {
		List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getId());
		if (validTokens.isEmpty()) {
			return;
		}

		validTokens.forEach(t -> t.setLoggedOut(true));
		tokenRepository.saveAll(validTokens);
	}

	/**
	 * Guarda un nuevo token de acceso y refresh para un usuario específico.
	 * @param accessToken El token de acceso generado.
	 * @param refreshToken El token de refresh generado.
	 * @param user El usuario al que se le asignarán los tokens. Esto es útil para
	 * mantener la sesión activa del usuario y permitir la renovación de tokens sin
	 * necesidad de volver a iniciar sesión.
	 */
	public void saveUserToken(String accessToken, String refreshToken, Customer user) {
		Token token = new Token();
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setLoggedOut(false);
		token.setCustomer(user);
		tokenRepository.save(token);
	}

	/**
	 * Busca un usuario por su token de refresh.
	 * @param refreshToken El token de refresh del usuario. Esto es útil para identificar
	 * al usuario que está intentando renovar sus tokens.
	 * @return El usuario asociado al token de refresh.
	 * @throws RuntimeException Si el usuario no se encuentra.
	 */
	public Customer findUserByRefreshToken(String refreshToken) {
		String username = jwtService.extractUsername(refreshToken);
		return customerRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	/**
	 * Crea nuevos tokens de acceso y refresh para un usuario específico.
	 * @param user El usuario para el cual se generarán los nuevos tokens. Esto es útil
	 * para renovar la sesión del usuario y proporcionar nuevos tokens de autenticación.
	 * @return Un objeto AuthenticationResponse que contiene los nuevos tokens.
	 */
	public AuthenticationResponse createNewTokens(Customer user) {
		String newAccessToken = jwtService.generateAccessToken(user);
		String newRefreshToken = jwtService.generateRefreshToken(user);

		revokeAllTokenByUser(user);
		saveUserToken(newAccessToken, newRefreshToken, user);

		return new AuthenticationResponse(newAccessToken, newRefreshToken, "New token generated");
	}

	/**
	 * Valida el token de refresh y genera nuevos tokens si es válido.
	 * @param refreshToken El token de refresh a validar.
	 * @param user El usuario asociado al token de refresh. Esto es útil para verificar la
	 * validez del token y generar nuevos tokens de autenticación.
	 * @return Un ResponseEntity que contiene los nuevos tokens o un error si el token no
	 * es válido.
	 */
	public ResponseEntity<AuthenticationResponse> validateAndGenerateNewTokens(String refreshToken, Customer user) {
		if (!jwtService.isValidRefreshToken(refreshToken, user)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		AuthenticationResponse response = createNewTokens(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Maneja la solicitud de renovación de tokens.
	 * @param request La solicitud HTTP que contiene el token de refresh en el encabezado
	 * Authorization. Esto es útil para permitir a los usuarios renovar sus tokens sin
	 * necesidad de volver a iniciar sesión.
	 * @return Un ResponseEntity que contiene los nuevos tokens o un error si el token no
	 * es válido.
	 * @param request
	 * @return
	 */
	public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String refreshToken = authHeader.substring(7);

		if (authHeader.startsWith("Bearer ")) {
			Customer user = findUserByRefreshToken(refreshToken);
			return validateAndGenerateNewTokens(refreshToken, user);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
