package com.onlineshop.service.cart.impl;

import com.onlineshop.domain.vo.CartDetailsRequest;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.auth.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// Service que implementa la lógica para buscar un carrito y un producto
@Slf4j
@Service
@RequiredArgsConstructor
public class CartFindQueryServiceImpl {

	private final ProductJpaRepository productJpaRepository;

	private final CartJpaRepository cartJpaRepository;

	private final AuthenticationService authenticationService;

	/**
	 * Método que busca un producto por su ID.
	 *
	 * @param details Objeto CartDetailsRequest que contiene el ID del producto a buscar.
	 * @return Product objeto que representa el producto encontrado.
	 * @throws BusinessException si el producto no se encuentra.
	 */
	public Product findProductById(CartDetailsRequest details) {
		return productJpaRepository.findById(details.getProductId()).orElseThrow(() -> {
			log.error(AppErrorCode.ERROR_PRODUCT_NOT_FOUND.getMessage());
			return new BusinessException(AppErrorCode.ERROR_PRODUCT_NOT_FOUND);
		});
	}

	/**
	 * Método que busca un carrito por su ID.
	 *
	 * @param id ID del carrito a buscar.
	 * @return Cart objeto que representa el carrito encontrado.
	 * @throws BusinessException si el carrito no se encuentra o no pertenece al cliente autenticado.
	 */
	@Transactional
	public Cart findCartById(Long id) {
		Cart cart = cartJpaRepository.findById(id)
			.orElseThrow(() -> new BusinessException(AppErrorCode.ERROR_CART_NOT_FOUND));
		return checkCartInCustomerCart(cart);
	}

	/**
	 * Método que busca un carrito por su ID y lo valida en los carritos del cliente autenticado.
	 *
	 * @param cart Objeto Cart que contiene el ID del carrito a buscar.
	 * @return Cart objeto que representa el carrito encontrado y validado.
	 * @throws BusinessException si el carrito no se encuentra o no pertenece al cliente autenticado.
	 */
	private Cart checkCartInCustomerCart(Cart cart) {
		List<Cart> customerCarts = getCustomerCarts();
		validateCartInCustomerCarts(cart, customerCarts);
		return cart;
	}

	/**
	 * Método que obtiene los carritos del cliente autenticado.
	 *
	 * @return Lista de objetos Cart que representan los carritos del cliente.
	 */
	private List<Cart> getCustomerCarts() {
		List<Cart> customerCarts = authenticationService.findUserByTokenAccess().getCart();
		return customerCarts != null ? customerCarts : Collections.emptyList();
	}

	/**
	 * Método que valida si el carrito pertenece a los carritos del cliente autenticado.
	 *
	 * @param cart          Objeto Cart que se va a validar.
	 * @param customerCarts Lista de carritos del cliente autenticado.
	 * @throws BusinessException si el carrito no pertenece al cliente autenticado.
	 */
	private void validateCartInCustomerCarts(Cart cart, List<Cart> customerCarts) {
		if (!customerCarts.contains(cart)) {
			log.error(AppErrorCode.ERROR_UNFORBIDDEN_CART.getMessage());
			throw new BusinessException(AppErrorCode.ERROR_UNFORBIDDEN_CART);
		}
	}

}
