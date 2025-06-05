package com.onlineshop.service.cart;

import com.onlineshop.repository.entities.Cart;

import java.util.List;

// Interface que define el servicio para obtener todos los carritos
public interface CartFindAllService {

	List<Cart> findAllCarts();

}
