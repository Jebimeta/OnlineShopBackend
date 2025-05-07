package com.onlineshop.config;

import com.onlineshop.repository.jpa.TokenJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@RequiredArgsConstructor
@Configuration
public class CustomLogoutHandler implements LogoutHandler {

	private static final String BEARER_PREFIX = "Bearer ";

	private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

	private final TokenJpaRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		String token = extractTokenFromRequest(request);
		if (token != null)
			invalidateToken(token);

	}

	private String extractTokenFromRequest(HttpServletRequest request) {
		String authHeader = getAuthorizationHeader(request);
		return isBearerToken(authHeader) ? extractBearerToken(authHeader) : null;
	}

	private String getAuthorizationHeader(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	private boolean isBearerToken(String authHeader) {
		return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
	}

	private String extractBearerToken(String authHeader) {
		return authHeader.substring(BEARER_PREFIX_LENGTH);
	}

	private void invalidateToken(String token) {
		tokenRepository.findByAccessToken(token).ifPresent(storedToken -> {
			storedToken.setLoggedOut(true);
			tokenRepository.save(storedToken);
		});
	}

}
