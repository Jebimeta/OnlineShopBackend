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

// Clase que filtra las peticiones HTTP para autenticar usuarios mediante JWT
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// Constantes para el encabezado de autorización y el prefijo del token Bearer
	private static final String AUTH_HEADER = "Authorization";

	// Prefijo utilizado para identificar tokens Bearer en el encabezado de autorización
	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtService jwtService;

	private final UserDetailsServiceImpl userDetailsService;

	/**
	 * Método que se ejecuta para filtrar las peticiones HTTP y autenticar al usuario si es necesario.
	 *
	 * @param request  la solicitud HTTP entrante
	 * @param response la respuesta HTTP a enviar
	 * @param filterChain la cadena de filtros a seguir
	 * @throws ServletException si ocurre un error en el procesamiento del filtro
	 * @throws IOException si ocurre un error de entrada/salida
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = extractToken(request.getHeader(AUTH_HEADER));

		if (token != null && !isAuthenticationPresent()) {
			authenticateUser(token, request);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Extrae el token JWT del encabezado de autorización.
	 *
	 * @param authHeader el encabezado de autorización
	 * @return el token JWT si está presente, o null si no lo está
	 */
	private String extractToken(String authHeader) {
		if (isBearerToken(authHeader)) {
			return authHeader.substring(BEARER_PREFIX.length());
		}
		return null;
	}

	/**
	 * Verifica si el encabezado de autorización contiene un token Bearer.
	 *
	 * @param authHeader el encabezado de autorización
	 * @return true si el encabezado contiene un token Bearer, false en caso contrario
	 */
	private boolean isBearerToken(String authHeader) {
		return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
	}

	/**
	 * Verifica si ya hay una autenticación presente en el contexto de seguridad.
	 *
	 * @return true si hay una autenticación presente, false en caso contrario
	 */
	private boolean isAuthenticationPresent() {
		return SecurityContextHolder.getContext().getAuthentication() != null;
	}

	/**
	 * Autentica al usuario utilizando el token JWT extraído.
	 *
	 * @param token el token JWT
	 * @param request la solicitud HTTP
	 */
	private void authenticateUser(String token, HttpServletRequest request) {
		String extractedUsername = jwtService.extractUsername(token);

		if (extractedUsername != null
				&& jwtService.isValid(token, userDetailsService.loadUserByUsername(extractedUsername))) {
			setAuthentication(userDetailsService.loadUserByUsername(extractedUsername), request);

		}
	}

/**
	 * Establece la autenticación del usuario en el contexto de seguridad.
	 *
	 * @param userDetails los detalles del usuario autenticado
	 * @param request la solicitud HTTP
	 */
	private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}
}