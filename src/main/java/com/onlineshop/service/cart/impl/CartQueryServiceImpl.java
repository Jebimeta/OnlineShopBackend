package com.onlineshop.service.cart.impl;

import com.onlineshop.repository.entities.Cart;
import com.onlineshop.service.cart.CartQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// Service que implementa la lógica para consultar un carrito por su ID
@Slf4j
@Service
@AllArgsConstructor
public class CartQueryServiceImpl implements CartQueryService {

	private final CartFindQueryServiceImpl cartFindQueryServiceImpl;

	/**
	 * Método que busca un carrito por su ID.
	 *
	 * @param id ID del carrito a buscar.
	 * @return Cart objeto que representa el carrito encontrado.
	 */
	@Override
	public Cart findCartById(Long id) {
		log.info("INIT - CartQueryServiceImpl -> findCartById()");

		Cart foundCart = cartFindQueryServiceImpl.findCartById(id);

		log.info("END - CartQueryServiceImpl -> findCartById() - The cart was found");
		return foundCart;
	}

}
