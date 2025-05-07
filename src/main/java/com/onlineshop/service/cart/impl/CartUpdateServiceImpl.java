package com.onlineshop.service.cart.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.CartUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartUpdateServiceImpl implements CartUpdateService {

	private final CartJpaRepository cartJpaRepository;

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

	private void validateCartRequest(Cart cartRequest) throws BusinessException {
		if (cartRequest == null) {
			throw new BusinessException(AppErrorCode.ERROR_INVALID_CART_REQUEST);
		}

	}

}
