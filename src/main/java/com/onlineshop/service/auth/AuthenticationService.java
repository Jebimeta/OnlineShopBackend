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

// Este servicio maneja la autenticación y registro de clientes.
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

	/**
	 * Autentica a un cliente utilizando su correo electrónico y contraseña.
	 * Si la autenticación es exitosa, genera un token de acceso y un token de actualización.
	 *
	 * @param loginRequest Objeto que contiene el correo electrónico y la contraseña del cliente.
	 * @return Un objeto AuthenticationResponse que contiene los tokens generados y un mensaje de éxito.
	 */
	public AuthenticationResponse authenticate(Customer loginRequest) {
		log.info("INIT - AuthenticationService -> authenticate()");

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

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

	/**
	 * Registra un nuevo cliente en el sistema.
	 * Si el correo electrónico ya está registrado, devuelve un mensaje de error.
	 *
	 * @param request Objeto que contiene los datos del cliente a registrar.
	 * @return Un objeto RegisterResponse que indica el resultado del registro.
	 */
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

/**
	 * Envía un correo electrónico de verificación al cliente después de registrarse.
	 * Si ocurre un error al enviar el correo, elimina el usuario del repositorio y devuelve un mensaje de error.
	 *
	 * @param user El cliente que se ha registrado.
	 * @return Un objeto RegisterResponse que indica el resultado del envío del correo electrónico.
	 */
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

	/**
	 * Verifica al usuario utilizando el token de verificación.
	 * Si la verificación es exitosa, actualiza el estado del cliente y genera nuevos tokens de acceso y actualización.
	 *
	 * @param token El token de verificación enviado al correo electrónico del cliente.
	 * @return Un objeto AuthenticationResponse que contiene los nuevos tokens generados y un mensaje de éxito.
	 */
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

	/**
	 * Obtiene el usuario autenticado actualmente.
	 * Utiliza el contexto de seguridad para obtener el nombre de usuario del principal autenticado.
	 *
	 * @return Un objeto CustomerResponse que representa al usuario autenticado.
	 */
	public CustomerResponse getAuthenticatedUser() {
		String username = getUsernameFromPrincipal(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		log.info("AuthenticatedUser: {}", username);
		Customer findedCustomer = repository.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not found"));
		return conversionService.convert(findedCustomer, CustomerResponse.class);
	}

	/**
	 * Obtiene el nombre de usuario del principal autenticado.
	 * Si el principal no es una instancia de UserDetails, lanza una excepción.
	 *
	 * @param principal El principal autenticado.
	 * @return El nombre de usuario del principal.
	 */
	private String getUsernameFromPrincipal(Object principal) {
		return (principal instanceof UserDetails userDetails) ? userDetails.getUsername()
				: throwUserNotAuthenticatedException();
	}

	/**
	 * Lanza una excepción de negocio indicando que el usuario no está autenticado.
	 * Utiliza un código de error específico para indicar el problema.
	 *
	 * @return Nunca retorna, lanza una excepción.
	 */
	private String throwUserNotAuthenticatedException() {
		throw new BusinessException(AppErrorCode.ERROR_NOT_AUTHENTICATED);
	}

	/**
	 * Busca al usuario autenticado por su token de acceso.
	 * Utiliza el servicio de consulta de clientes para obtener el cliente por su nombre de usuario.
	 *
	 * @return El cliente autenticado.
	 */
	public Customer findUserByTokenAccess() {
		CustomerResponse customerResponse = getAuthenticatedUser();
		return customerQueryService.getCustomerByUserName(customerResponse.getUsername());
	}
}
