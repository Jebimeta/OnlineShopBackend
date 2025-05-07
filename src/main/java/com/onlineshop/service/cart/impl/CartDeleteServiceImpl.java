package com.onlineshop.service.cart.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDeleteServiceImpl implements CartDeleteService {

	private final CartJpaRepository cartJpaRepository;

	@Override
	public String deleteCartById(Long id) {
		Cart cart = cartJpaRepository.findById(id)
			.orElseThrow(() -> new BusinessException(AppErrorCode.ERROR_CART_NOT_FOUND));
		cartJpaRepository.delete(cart);
		return "El carrito ha sido eliminado con éxito";
	}

}
