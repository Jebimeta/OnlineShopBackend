package com.onlineshop.service.cart.impl;

import com.onlineshop.domain.vo.CartDetailsRequest;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.entities.CartDetails;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.CartJpaRepository;
import com.onlineshop.service.cart.AddProductToCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddProductToCartServiceImpl implements AddProductToCartService {

	private final CartJpaRepository cartJpaRepository;

	private final CartFindQueryServiceImpl cartFindQueryServiceImpl;

	@Override
	public CartDetails addProductToCart(Long cartId, CartDetailsRequest request) {
		Cart foundCart = cartFindQueryServiceImpl.findCartById(cartId);
		Product addedProduct = cartFindQueryServiceImpl.findProductById(request);

		List<CartDetails> cartDetailsList = foundCart.getCartDetails();
		CartDetails cartDetails = new CartDetails();
		cartDetails.setCart(foundCart);
		cartDetails.setProduct(addedProduct);
		cartDetails.setQuantity(request.getQuantity());
		cartDetails.setPrice(addedProduct.getPrice() * request.getQuantity());
		cartDetailsList.add(cartDetails);

		foundCart.setCartDetails(cartDetailsList);
		foundCart.setDate(LocalDateTime.now());

		cartJpaRepository.save(foundCart);

		return cartDetails;
	}

}
