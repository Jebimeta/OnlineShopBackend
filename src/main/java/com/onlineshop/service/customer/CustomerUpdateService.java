package com.onlineshop.service.customer;

import com.onlineshop.repository.entities.Customer;

// Service que permite obtener un cliente por su nombre de usuario
public interface CustomerUpdateService {

	Customer updateCustomer(Customer customer);

}
