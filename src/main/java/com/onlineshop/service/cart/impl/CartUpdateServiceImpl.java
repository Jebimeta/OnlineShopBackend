package com.onlineshop.service.cart.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// Service que implementa la lógica para actualizar un carrito
@Service
@AllArgsConstructor
public class CartUpdateServiceImpl implements CartUpdateService {

	private final CartJpaRepository cartJpaRepository;

	/**
	 * Método que actualiza un carrito existente.
	 * @param cartId ID del carrito a actualizar.
	 * @param cartRequest Objeto Cart que contiene los nuevos detalles del carrito.
	 * @return Cart objeto que representa el carrito actualizado.
	 * @throws BusinessException si el carrito no se encuentra o si la solicitud es
	 * inválida.
	 */
	@Override
	public Cart updateCart(Long cartId, Cart cartRequest) throws BusinessException {
		validateCartRequest(cartRequest);

		Cart existingCart = cartJpaRepository.findById(cartId)
			.orElseThrow(() -> new BusinessException(AppErrorCode.ERROR_CART_NOT_FOUND));

		existingCart.setDate(cartRequest.getDate());
		existingCart.setCustomer(cartRequest.getCustomer());
		existingCart.setCartDetails(existingCart.getCartDetails());

		return cartJpaRepository.save(existingCart);
	}

	/**
	 * Método que valida la solicitud de actualización del carrito.
	 * @param cartRequest Objeto Cart que contiene los detalles del carrito a actualizar.
	 * @throws BusinessException si la solicitud es inválida.
	 */
	private void validateCartRequest(Cart cartRequest) throws BusinessException {
		if (cartRequest == null) {
			throw new BusinessException(AppErrorCode.ERROR_INVALID_CART_REQUEST);
		}
	}

}
