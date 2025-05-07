package com.onlineshop.service.cart;

import com.onlineshop.repository.entities.Cart;

import java.util.List;

public interface CartFindAllService {

	List<Cart> findAllCarts();

}
