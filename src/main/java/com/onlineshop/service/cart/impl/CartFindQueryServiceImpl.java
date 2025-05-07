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

@Slf4j
@Service
@RequiredArgsConstructor
public class CartFindQueryServiceImpl {

	private final ProductJpaRepository productJpaRepository;

	private final CartJpaRepository cartJpaRepository;

	private final AuthenticationService authenticationService;

	public Product findProductById(CartDetailsRequest details) {
		return productJpaRepository.findById(details.getProductId()).orElseThrow(() -> {
			log.error(AppErrorCode.ERROR_PRODUCT_NOT_FOUND.getMessage());
			return new BusinessException(AppErrorCode.ERROR_PRODUCT_NOT_FOUND);
		});
	}

	@Transactional
	public Cart findCartById(Long id) {
		Cart cart = cartJpaRepository.findById(id)
			.orElseThrow(() -> new BusinessException(AppErrorCode.ERROR_CART_NOT_FOUND));
		return checkCartInCustomerCart(cart);
	}

	private Cart checkCartInCustomerCart(Cart cart) {
		List<Cart> customerCarts = getCustomerCarts();
		validateCartInCustomerCarts(cart, customerCarts);
		return cart;
	}

	private List<Cart> getCustomerCarts() {
		List<Cart> customerCarts = authenticationService.findUserByTokenAccess().getCart();
		return customerCarts != null ? customerCarts : Collections.emptyList();
	}

	private void validateCartInCustomerCarts(Cart cart, List<Cart> customerCarts) {
		if (!customerCarts.contains(cart)) {
			log.error(AppErrorCode.ERROR_UNFORBIDDEN_CART.getMessage());
			throw new BusinessException(AppErrorCode.ERROR_UNFORBIDDEN_CART);
		}
	}

}
