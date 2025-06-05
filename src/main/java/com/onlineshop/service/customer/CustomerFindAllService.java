package com.onlineshop.service.customer;

import com.onlineshop.repository.entities.Customer;

import java.util.List;

// Service que permite obtener todos los clientes
public interface CustomerFindAllService {

	List<Customer> findAllCustomers();

}
