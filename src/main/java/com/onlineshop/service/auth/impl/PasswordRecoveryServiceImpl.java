package com.onlineshop.service.auth.impl;

import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.domain.vo.PasswordResetConfirmRequest;
import com.onlineshop.domain.vo.PasswordResetRequest;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.service.auth.PasswordRecoveryService;
import com.onlineshop.service.customer.CustomerUpdateService;
import com.onlineshop.service.customer.CustomerVerificationTokenService;
import com.onlineshop.util.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Este servicio maneja la recuperación de contraseñas para los clientes
@Service
@RequiredArgsConstructor
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

	private final CustomerUpdateService customerUpdateService;

	private final MailSenderService mailSenderService;

	private final CustomerVerificationTokenService customerVerificationTokenService;

	/**
	 * Envía un correo electrónico para la recuperación de contraseña.
	 * @param passwordResetRequest Objeto que contiene el correo electrónico del cliente.
	 * @return Un objeto EmailResponse que contiene el estado del envío del correo.
	 */
	@Override
	public EmailResponse sendEmailToRecoveryPassword(PasswordResetRequest passwordResetRequest) {
		return mailSenderService.sendPasswordRecoveryEmail(passwordResetRequest.getEmail());
	}

	/**
	 * Confirma la recuperación de contraseña y actualiza la contraseña del cliente.
	 * @param passwordResetConfirmRequest Objeto que contiene el token de verificación y
	 * la nueva contraseña.
	 * @return El objeto Customer actualizado con la nueva contraseña.
	 */
	@Override
	public Customer confirmRecoveryPassword(PasswordResetConfirmRequest passwordResetConfirmRequest) {
		Customer customer = customerVerificationTokenService
			.findCustomerByVerificationToken(passwordResetConfirmRequest.getVerificationToken());

		customer.setPassword(passwordResetConfirmRequest.getNewPassword());
		customerUpdateService.updateCustomer(customer);

		return customer;
	}

}
