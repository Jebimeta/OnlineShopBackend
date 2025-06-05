package com.onlineshop.service.auth;

import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.domain.vo.PasswordResetConfirmRequest;
import com.onlineshop.domain.vo.PasswordResetRequest;
import com.onlineshop.repository.entities.Customer;

// Este servicio maneja la recuperación de contraseñas para los clientes
public interface PasswordRecoveryService {

	// Envía un correo electrónico para la recuperación de contraseña
	public EmailResponse sendEmailToRecoveryPassword(PasswordResetRequest passwordResetRequest);

	// Confirma la recuperación de contraseña y actualiza la contraseña del cliente
	public Customer confirmRecoveryPassword(PasswordResetConfirmRequest passwordResetConfirmRequest);

}
