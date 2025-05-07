package com.onlineshop.service.customer;

import com.onlineshop.repository.entities.Customer;

public interface CustomerVerificationTokenService {

	String generateVerificationToken(Customer customer);

	Customer verifyCustomerByToken(String token);

	Customer findCustomerByVerificationToken(String token);

}
