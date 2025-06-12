package com.onlineshop.service.cart.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.jpa.CartDetailsJpaRepository;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

// Service que implementa la lógica para crear un carrito con detalles
@Service
@Log4j2
@RequiredArgsConstructor
public class CartCreateServiceImpl implements CartCreateService {

	private final CartJpaRepository cartJpaRepository;

	private final CartDetailsJpaRepository cartDetailsJpaRepository;

	/**
	 * Método que crea un nuevo carrito con los detalles proporcionados.
	 * @param cart Objeto Cart que contiene los detalles del carrito a crear.
	 * @return Cart objeto que representa el carrito creado.
	 * @throws BusinessException si no se proporcionan detalles del carrito.
	 */
	@Override
	public Cart createCartWithCartDetails(Cart cart) {
		log.info("INIT - CartCreateServiceImpl -> createCartWithCartDetails()");

		if (!cart.getCartDetails().isEmpty()) {
			log.info("Saving the cartDetails.");
			cartDetailsJpaRepository.saveAll(cart.getCartDetails());
		}
		else {
			throw new BusinessException(AppErrorCode.ERROR_CREATE_CART);
		}
		log.info("Saving the new shopping cart.");
		Cart createdCart = cartJpaRepository.save(cart);
		log.info("END - CartCreateServiceImpl -> createCartWithCartDetails()");
		return createdCart;
	}

}