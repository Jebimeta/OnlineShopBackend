package com.onlineshop.service.customer.impl;

import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service que permite eliminar un cliente por su nombre de usuario
@Service
@RequiredArgsConstructor
public class CustomerDeleteServiceImpl implements CustomerDeleteService {

	private final CustomerJpaRepository customerJpaRepository;

	/**
	 * Elimina un cliente por su nombre de usuario.
	 *
	 * @param username el nombre de usuario del cliente a eliminar
	 * @return null si el cliente fue eliminado correctamente
	 * @throws UsernameNotFoundException si no se encuentra un cliente con el nombre de usuario proporcionado
	 */
	@Override
	public Void deleteCustomerByUsername(String username) {
		Optional<Customer> customerEntity = customerJpaRepository.findByUsername(username);

		if (customerEntity.isPresent()) {
			customerJpaRepository.delete(customerEntity.get());
			return null;
		}
		else {
			throw new UsernameNotFoundException("User not found");
		}

	}

}
