package com.onlineshop.service.customer.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerVerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// Service que permite gestionar el token de verificación de un cliente
@Service
@RequiredArgsConstructor
public class CustomerVerificationTokenServiceImpl implements CustomerVerificationTokenService {

	private final CustomerJpaRepository customerRepository;

	/**
	 * Genera un token de verificación único para el cliente.
	 * @param customer el cliente para el cual se genera el token
	 * @return un token de verificación único
	 */
	@Override
	public String generateVerificationToken(Customer customer) {
		return UUID.randomUUID().toString();
	}

	/**
	 * Verifica al cliente utilizando el token proporcionado.
	 * @param token el token de verificación del cliente
	 * @return el cliente verificado
	 * @throws BusinessException si el token es inválido o no se encuentra un cliente
	 * asociado
	 */
	@Override
	public Customer verifyCustomerByToken(String token) {
		Optional<Customer> customerOptional = customerRepository.findByVerificationToken(token);
		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			customer.setStatus(true);
			customer.setVerificationToken(null);
			customerRepository.save(customer);
			return customer;
		}
		else {
			throw new BusinessException(AppErrorCode.ERROR_INVALID_VERIFICATION_TOKEN);
		}
	}

	/**
	 * Busca un cliente por su token de verificación.
	 * @param token el token de verificación del cliente
	 * @return el cliente asociado al token
	 * @throws BusinessException si no se encuentra un cliente con el token proporcionado
	 */
	public Customer findCustomerByVerificationToken(String token) {
		Optional<Customer> customerOptional = customerRepository.findByVerificationToken(token);
		if (customerOptional.isPresent()) {
			return customerOptional.get();
		}
		else {
			throw new BusinessException(AppErrorCode.ERROR_INVALID_VERIFICATION_TOKEN);
		}
	}

}
