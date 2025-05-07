package com.onlineshop.service.customer;

import com.onlineshop.repository.entities.Customer;

import java.util.List;

public interface CustomerFindAllService {

	List<Customer> findAllCustomers();

}
