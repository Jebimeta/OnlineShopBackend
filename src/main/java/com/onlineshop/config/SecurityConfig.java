package com.onlineshop.config;

import com.onlineshop.filter.JwtAuthenticationFilter;
import com.onlineshop.service.auth.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.onlineshop.config.enums.OnlineShopApiEndpoints.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsServiceImp;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final CustomLogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		configureCsrf(http);
		configureAuthorization(http);
		configureUserDetailsService(http);
		configureSessionManagement(http);
		configureFilters(http);
		configureExceptionHandling(http);
		configureLogout(http);
		return http.build();
	}

	private void configureCsrf(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
	}

	private void configureAuthorization(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(registry -> registry
			.requestMatchers(SWAGGER_UI_HTML.getUrl(), SWAGGER_UI.getUrl(), API_DOCS.getUrl())
			.permitAll()
			.requestMatchers(LOGIN_URL.getUrl(), REGISTER_URL.getUrl(), CREATE_CART_URL.getUrl(),
					REFRESH_TOKEN_URL.getUrl(), PRODUCTS_LIST_URL.getUrl(), OBTAIN_PRODUCT_BY_ID_URL.getUrl(),
					IMAGES_URL.getUrl(), VERIFY_TOKEN_URL.getUrl(), SEND_EMAIL_URL.getUrl(), ADD_PRODUCT_URL.getUrl(),
					OBTAIN_CART_URL.getUrl(), DELETE_CART_URL.getUrl(), OBTAIN_ALL_CARTS_URL.getUrl(),
					REMOVE_PRODUCT_FROM_CART_URL.getUrl(), OBTAIN_ALL_PURCHASES_URL.getUrl(),
					OBTAIN_PURCHASE_BY_ID_URL.getUrl(), REQUEST_PURCHASE_CANCELLATION.getUrl())
			.permitAll()
			.requestMatchers(CREATE_PRODUCT_URL.getUrl(), UPDATE_PRODUCT_URL.getUrl(), DELETE_PRODUCT_URL.getUrl(),
					USERS_LIST_URL.getUrl(), OBTAIN_USER_BY_ID_URL.getUrl(), VALIDATION_PURCHASE_CANCELLATION.getUrl())
			.hasRole("ROLE_ADMIN")
			.requestMatchers(DELETE_USER_URL.getUrl(), UPDATE_USER_URL.getUrl(), AUTHENTICATED_USER_INFO_URL.getUrl(),
					SEND_EMAIL_URL.getUrl(), MAKE_PURCHASE_URL.getUrl())
			.authenticated());
	}

	private void configureUserDetailsService(HttpSecurity http) throws Exception {
		http.userDetailsService(userDetailsServiceImp);
	}

	private void configureSessionManagement(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	}

	private void configureFilters(HttpSecurity http) {
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	private void configureExceptionHandling(HttpSecurity http) throws Exception {
		http.exceptionHandling(exception -> exception
			.accessDeniedHandler(
					(request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value()))
			.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
	}

	private void configureLogout(HttpSecurity http) throws Exception {
		http.logout(logout -> logout.logoutUrl("/logout")
			.addLogoutHandler(logoutHandler)
			.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}

}