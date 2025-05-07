package com.onlineshop.service.customer;

import com.onlineshop.repository.entities.Customer;

public interface CustomerQueryService {

	Customer getCustomerByUserName(String username);

}
