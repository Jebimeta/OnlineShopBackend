package com.onlineshop.service.customer.impl;

import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDeleteServiceImpl implements CustomerDeleteService {

	private final CustomerJpaRepository customerJpaRepository;

	@Override
	public Void deleteCustomerByUsername(String username) {
		Optional<Customer> customerEntity = customerJpaRepository.findByUsername(username);

		if (customerEntity.isPresent()) {
			Customer customer = customerEntity.get();
			customer.setStatus(false);
			customerJpaRepository.save(customer);
			return null;
		}
		else {
			throw new UsernameNotFoundException("User not found");
		}

	}

}
