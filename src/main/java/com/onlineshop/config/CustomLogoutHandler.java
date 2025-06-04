package com.onlineshop.config;

import com.onlineshop.repository.jpa.TokenJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

// Clase que maneja el cierre de sesión personalizado.
@RequiredArgsConstructor
@Configuration
public class CustomLogoutHandler implements LogoutHandler {

	private static final String BEARER_PREFIX = "Bearer ";

	private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

	private final TokenJpaRepository tokenRepository;

	// Método que invalida el token de acceso al cerrar sesión.
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		String token = extractTokenFromRequest(request);
		if (token != null)
			invalidateToken(token);

	}

	// Método que extrae el token de acceso del encabezado de autorización.
	private String extractTokenFromRequest(HttpServletRequest request) {
		String authHeader = getAuthorizationHeader(request);
		return isBearerToken(authHeader) ? extractBearerToken(authHeader) : null;
	}

	// Métodos auxiliares para manejar el encabezado de autorización y el token.
	private String getAuthorizationHeader(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	// Verifica si el encabezado de autorización contiene un token Bearer.
	private boolean isBearerToken(String authHeader) {
		return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
	}

	// Extrae el token Bearer del encabezado de autorización.
	private String extractBearerToken(String authHeader) {
		return authHeader.substring(BEARER_PREFIX_LENGTH);
	}

	// Método que invalida el token de acceso almacenado en la base de datos.
	private void invalidateToken(String token) {
		tokenRepository.findByAccessToken(token).ifPresent(storedToken -> {
			storedToken.setLoggedOut(true);
			tokenRepository.save(storedToken);
		});
	}

}
