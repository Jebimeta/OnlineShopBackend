package com.onlineshop.service.cart.impl;

import com.onlineshop.repository.entities.Cart;
import com.onlineshop.service.cart.CartQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CartQueryServiceImpl implements CartQueryService {

	private final CartFindQueryServiceImpl cartFindQueryServiceImpl;

	@Override
	public Cart findCartById(Long id) {
		log.info("INIT - CartQueryServiceImpl -> findCartById()");

		Cart foundCart = cartFindQueryServiceImpl.findCartById(id);

		log.info("END - CartQueryServiceImpl -> findCartById() - The cart was found");
		return foundCart;
	}

}
