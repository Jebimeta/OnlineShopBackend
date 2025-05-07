package com.onlineshop.service.cart;

import com.onlineshop.exception.BusinessException;

import com.onlineshop.repository.entities.Cart;

public interface CartUpdateService {

	Cart updateCart(Long cartId, Cart cartRequest) throws BusinessException;

}
