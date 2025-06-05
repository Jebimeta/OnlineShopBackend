package com.onlineshop.service.customer.factory;

import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.enums.RolEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Factory que permite crear un cliente a partir de una solicitud
@Component
@RequiredArgsConstructor
public class CustomerRequestFactory {

	private final PasswordEncoder passwordEncoder;

	/**
	 * Crea un objeto Customer a partir de una solicitud de cliente.
	 *
	 * @param request el objeto Customer que contiene los datos de la solicitud
	 * @return un nuevo objeto Customer con los datos proporcionados
	 */
	public Customer createCustomerRequest(Customer request) {
		Customer user = new Customer();
		user.setName(request.getName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setUsername(request.getEmail());
		user.setSurname(request.getSurname());
		user.setSurname2(request.getSurname2());
		user.setAddress(request.getAddress());
		user.setProvince(request.getProvince());
		user.setRegion(request.getRegion());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setStatus(false);
		user.setRol(RolEnum.USER);

		return user;
	}

}
