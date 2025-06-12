package com.onlineshop.service.cart.impl;

import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// Service que implementa la lógica para consultar un carrito por su ID
@Slf4j
@Service
@AllArgsConstructor
public class CartQueryServiceImpl implements CartQueryService {

	private final CartFindQueryServiceImpl cartFindQueryServiceImpl;

	private final CartJpaRepository cartJpaRepository;

	/**
	 * Método que busca un carrito por su ID.
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

	@Override
	public List<Product> findProductsInCartByCustomerId(Long customerId) {
		log.info("INIT - CartQueryServiceImpl -> findProductsInCartByCustomerId()");
		List<Product> products = cartJpaRepository.findProductsInCartByCustomerId(customerId);
		log.info("END - CartQueryServiceImpl -> findProductsInCartByCustomerId() - Products found: {}",
				products.size());
		return products;
	}

}
