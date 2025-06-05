package com.onlineshop.service.cart.impl;

import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Service que implementa la lógica para obtener todos los carritos
@Service
@RequiredArgsConstructor
public class CartFindAllServiceImpl implements CartFindAllService {

	private final CartJpaRepository cartJpaRepository;

	/**
	 * Método que obtiene todos los carritos.
	 *
	 * @return Lista de objetos Cart que representan todos los carritos.
	 */
	@Override
	public List<Cart> findAllCarts() {
		return cartJpaRepository.findAll();
	}

}
