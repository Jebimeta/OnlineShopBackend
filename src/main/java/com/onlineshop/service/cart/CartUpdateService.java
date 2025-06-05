package com.onlineshop.service.cart;

import com.onlineshop.exception.BusinessException;

import com.onlineshop.repository.entities.Cart;

// Interface que define el servicio para actualizar un carrito
public interface CartUpdateService {

	Cart updateCart(Long cartId, Cart cartRequest) throws BusinessException;

}
