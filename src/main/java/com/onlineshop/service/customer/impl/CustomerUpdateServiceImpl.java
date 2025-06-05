package com.onlineshop.service.customer.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service que permite actualizar un cliente
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerUpdateServiceImpl implements CustomerUpdateService {

	private final CustomerJpaRepository customerJpaRepository;

	private final PasswordEncoder passwordEncoder;

	/**
	 * Actualiza un cliente con los datos proporcionados.
	 *
	 * @param customerUpdateRequest el objeto Customer con los datos a actualizar
	 * @return el cliente actualizado
	 * @throws UsernameNotFoundException si no se encuentra un cliente con el email proporcionado
	 */
	@Override
	public Customer updateCustomer(Customer customerUpdateRequest) {
		Optional<Customer> customer = customerJpaRepository.findByEmail(customerUpdateRequest.getEmail());
		if (customer.isPresent()) {
			Customer findedCustomer = customer.get();
			findedCustomer.setName(customerUpdateRequest.getName());
			findedCustomer.setPassword(passwordEncoder.encode(customerUpdateRequest.getPassword()));
			findedCustomer.setSurname(customerUpdateRequest.getSurname());
			findedCustomer.setSurname2(customerUpdateRequest.getSurname2());
			findedCustomer.setAddress(customerUpdateRequest.getAddress());
			findedCustomer.setProvince(customerUpdateRequest.getProvince());
			findedCustomer.setRegion(customerUpdateRequest.getRegion());
			findedCustomer.setEmail(customerUpdateRequest.getEmail());
			findedCustomer.setPhone(customerUpdateRequest.getPhone());
			findedCustomer.setStatus(customerUpdateRequest.getStatus());
			findedCustomer.setRol(findedCustomer.getRol());
			findedCustomer.setPurchases(findedCustomer.getPurchases());
			findedCustomer.setTokens(findedCustomer.getTokens());
			return customerJpaRepository.save(findedCustomer);
		}
		else {
			throw new UsernameNotFoundException(AppErrorCode.ERROR_USER_NOT_FOUND.getMessage());
		}
	}

}
