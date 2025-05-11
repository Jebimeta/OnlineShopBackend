package com.onlineshop.service.auth;

import com.onlineshop.domain.vo.AuthenticationResponse;
import com.onlineshop.domain.vo.CustomerResponse;
import com.onlineshop.domain.vo.RegisterResponse;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerQueryService;
import com.onlineshop.service.customer.CustomerVerificationTokenService;
import com.onlineshop.service.customer.factory.CustomerRequestFactory;
import com.onlineshop.util.JwtService;
import com.onlineshop.util.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

	private final CustomerJpaRepository repository;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	private final CustomerVerificationTokenService tokenService;

	private final MailSenderService mailSenderService;

	private final CustomerTokenService customerTokenService;

	private final ConversionService conversionService;

	private final CustomerQueryService customerQueryService;

	private final CustomerRequestFactory customerRequestFactory;

	public AuthenticationResponse authenticate(Customer loginRequest) {
		log.info("INIT - AuthenticationService -> authenticate()");

		authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		Customer user = repository.findByEmail(loginRequest.getEmail()).orElseThrow();

		if (Boolean.FALSE.equals(user.getStatus())) {
			throw new BusinessException(AppErrorCode.ERROR_NOT_VERIFIED_ACCOUNT);
		}

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);

		customerTokenService.revokeAllTokenByUser(user);
		customerTokenService.saveUserToken(accessToken, refreshToken, user);

		log.info("END - AuthenticationService -> authenticate()");

		return new AuthenticationResponse(accessToken, refreshToken, "User login was successful");
	}

	public RegisterResponse register(Customer request) {
		if (repository.findByEmail(request.getEmail()).isPresent()) {
			log.info("User already exists in database");
			return new RegisterResponse(null, null, "User already exists");
		}
		log.info("INIT - AuthenticationService -> register()");
		Customer user = customerRequestFactory.createCustomerRequest(request);

		String verificationToken = tokenService.generateVerificationToken(user);
		user.setVerificationToken(verificationToken);

		RegisterResponse response = sendVerificationEmail(user);
		log.info("END - AuthenticationService -> register()");
		return response;
	}

	private RegisterResponse sendVerificationEmail(Customer user) {
		try {
			log.info("Attempting to register user: {}", user.getEmail());
			repository.save(user);
			mailSenderService.sendVerificationEmail(user);
		}
		catch (BusinessException e) {
			log.error("Error sending verification email", e);
			repository.delete(user);
			return new RegisterResponse(null, null, "Failed to send verification email. Please try again.");
		}
		return new RegisterResponse(null, null,
				"User registered successfully. Please check your email to verify your account.");
	}

	public AuthenticationResponse verifyUser(String token) {
		try {
			log.info("INIT - AuthenticationService -> verifyUser()");
			Customer customer = tokenService.verifyCustomerByToken(token);
			customer.setStatus(true);
			customer.setVerificationToken(null);
			repository.save(customer);
			log.info("END - AuthenticationService -> verifyUser() - {} has been successfully verified.",
					customer.getEmail());
			String accessToken = jwtService.generateAccessToken(customer);
			String refreshToken = jwtService.generateRefreshToken(customer);

			customerTokenService.revokeAllTokenByUser(customer);
			customerTokenService.saveUserToken(accessToken, refreshToken, customer);
			log.debug("Access Token: {} , Refresh Token: {}", accessToken, refreshToken);
			return new AuthenticationResponse(accessToken, refreshToken, "User login was successful");
		}
		catch (BusinessException e) {
			throw new BusinessException(AppErrorCode.ERROR_NOT_VERIFIED_ACCOUNT);
		}
	}

	public CustomerResponse getAuthenticatedUser() {
		String username = getUsernameFromPrincipal(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		log.info("AuthenticatedUser: {}", username);
		Customer findedCustomer = repository.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not found"));
		return conversionService.convert(findedCustomer, CustomerResponse.class);
	}

	private String getUsernameFromPrincipal(Object principal) {
		return (principal instanceof UserDetails userDetails) ? userDetails.getUsername()
				: throwUserNotAuthenticatedException();
	}

	private String throwUserNotAuthenticatedException() {
		throw new BusinessException(AppErrorCode.ERROR_NOT_AUTHENTICATED);
	}

	public Customer findUserByTokenAccess() {
		CustomerResponse customerResponse = getAuthenticatedUser();
		return customerQueryService.getCustomerByUserName(customerResponse.getUsername());
	}

}
