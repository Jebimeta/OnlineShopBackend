package com.onlineshop.service.customer;

import com.onlineshop.repository.entities.Customer;

// Service que permite obtener un cliente por su nombre de usuario
public interface CustomerVerificationTokenService {

	// Método que genera un token de verificación para un cliente
	String generateVerificationToken(Customer customer);

	// Método que verifica un cliente utilizando un token
	Customer verifyCustomerByToken(String token);

	// Método que busca un cliente por su token de verificación
	Customer findCustomerByVerificationToken(String token);

}
