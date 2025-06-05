package com.onlineshop.service.cart.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.RemoveProductFromCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Service que implementa la lógica para eliminar un producto de un carrito
@Service
@RequiredArgsConstructor
public class RemoveProductFromCartServiceImpl implements RemoveProductFromCartService {

	private final CartJpaRepository cartJpaRepository;

	/**
	 * Método que elimina un producto de un carrito por su ID.
	 *
	 * @param cartId ID del carrito del cual se eliminará el producto.
	 * @param cartDetailsId ID del detalle del carrito que representa el producto a eliminar.
	 * @return Mensaje de éxito si el producto fue eliminado correctamente.
	 * @throws BusinessException si el producto no se encuentra en el carrito.
	 */
	@Override
	public String removeProductFromCart(Long cartId, Long cartDetailsId) {
		try {
			cartJpaRepository.deleteProductFromCart(cartId, cartDetailsId);
			return "The product was deleted from cart successfully";
		}
		catch (BusinessException e) {
			throw new BusinessException(AppErrorCode.ERROR_PRODUCT_NOT_FOUND);
		}
	}
}
