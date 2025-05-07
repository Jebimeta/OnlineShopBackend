package com.onlineshop.service.cart.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.RemoveProductFromCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveProductFromCartServiceImpl implements RemoveProductFromCartService {

	private final CartJpaRepository cartJpaRepository;

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
