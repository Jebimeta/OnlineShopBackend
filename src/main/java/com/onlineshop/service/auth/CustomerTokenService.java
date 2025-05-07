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

@Service
@RequiredArgsConstructor
public class CustomerTokenService {

	private final TokenJpaRepository tokenRepository;

	private final JwtService jwtService;

	private final CustomerJpaRepository customerRepository;

	public void revokeAllTokenByUser(Customer user) {
		List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getId());
		if (validTokens.isEmpty()) {
			return;
		}

		validTokens.forEach(t -> t.setLoggedOut(true));
		tokenRepository.saveAll(validTokens);
	}

	public void saveUserToken(String accessToken, String refreshToken, Customer user) {
		Token token = new Token();
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setLoggedOut(false);
		token.setCustomer(user);
		tokenRepository.save(token);
	}

	public Customer findUserByRefreshToken(String refreshToken) {
		String username = jwtService.extractUsername(refreshToken);
		return customerRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public AuthenticationResponse createNewTokens(Customer user) {
		String newAccessToken = jwtService.generateAccessToken(user);
		String newRefreshToken = jwtService.generateRefreshToken(user);

		revokeAllTokenByUser(user);
		saveUserToken(newAccessToken, newRefreshToken, user);

		return new AuthenticationResponse(newAccessToken, newRefreshToken, "New token generated");
	}

	public ResponseEntity<AuthenticationResponse> validateAndGenerateNewTokens(String refreshToken, Customer user) {
		if (!jwtService.isValidRefreshToken(refreshToken, user)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		AuthenticationResponse response = createNewTokens(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

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
