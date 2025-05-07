package com.onlineshop.service.cart.impl;

import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFindAllServiceImpl implements CartFindAllService {

	private final CartJpaRepository cartJpaRepository;

	@Override
	public List<Cart> findAllCarts() {
		return cartJpaRepository.findAll();
	}

}
