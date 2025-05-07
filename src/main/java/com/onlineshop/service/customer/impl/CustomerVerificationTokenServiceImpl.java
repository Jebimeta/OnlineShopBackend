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

@Service
@RequiredArgsConstructor
public class CustomerVerificationTokenServiceImpl implements CustomerVerificationTokenService {

	private final CustomerJpaRepository customerRepository;

	@Override
	public String generateVerificationToken(Customer customer) {
		return UUID.randomUUID().toString();
	}

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
