package com.onlineshop.filter;

import com.onlineshop.util.JwtService;
import com.onlineshop.service.auth.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER = "Authorization";

	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtService jwtService;

	private final UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = extractToken(request.getHeader(AUTH_HEADER));

		if (token != null && !isAuthenticationPresent()) {
			authenticateUser(token, request);
		}

		filterChain.doFilter(request, response);
	}

	private String extractToken(String authHeader) {
		if (isBearerToken(authHeader)) {
			return authHeader.substring(BEARER_PREFIX.length());
		}
		return null;
	}

	private boolean isBearerToken(String authHeader) {
		return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
	}

	private boolean isAuthenticationPresent() {
		return SecurityContextHolder.getContext().getAuthentication() != null;
	}

	private void authenticateUser(String token, HttpServletRequest request) {
		String extractedUsername = jwtService.extractUsername(token);

		if (extractedUsername != null
				&& jwtService.isValid(token, userDetailsService.loadUserByUsername(extractedUsername))) {
			setAuthentication(userDetailsService.loadUserByUsername(extractedUsername), request);

		}
	}

	private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

}
