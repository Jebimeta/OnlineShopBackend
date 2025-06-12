package com.onlineshop.service.customer.impl;

import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service que permite obtener un cliente por su nombre de usuario
@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

	private final CustomerJpaRepository customerJpaRepository;

	/**
	 * Obtiene un cliente por su nombre de usuario.
	 * @param username el nombre de usuario del cliente a buscar
	 * @return el cliente encontrado
	 * @throws UsernameNotFoundException si no se encuentra un cliente con el nombre de
	 * usuario proporcionado
	 */
	@Override
	public Customer getCustomerByUserName(String username) {
		Optional<Customer> customerEntity = customerJpaRepository.findByUsername(username);

		if (customerEntity.isPresent()) {
			return customerEntity.get();
		}
		else {
			throw new UsernameNotFoundException("User not found");
		}
	}

}
