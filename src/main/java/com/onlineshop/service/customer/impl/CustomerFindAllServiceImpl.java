package com.onlineshop.service.customer.impl;

import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import com.onlineshop.service.customer.CustomerFindAllService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// Service que permite obtener todos los clientes
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerFindAllServiceImpl implements CustomerFindAllService {

	private final CustomerJpaRepository customerJpaRepository;

	/**
	 * Obtiene todos los clientes.
	 *
	 * @return una lista de todos los clientes
	 */
	@Override
	public List<Customer> findAllCustomers() {
		log.info("INIT - CustomerFindAllServiceImpl -> findAllCustomers()");
		List<Customer> foundCustomers = customerJpaRepository.findAll();
		for (Customer customer : foundCustomers) {
			log.debug("Customer - {}", customer.getEmail());
		}
		log.info("END - CustomerFindAllServiceImpl -> findAllCustomers()");
		return foundCustomers;
	}

}
