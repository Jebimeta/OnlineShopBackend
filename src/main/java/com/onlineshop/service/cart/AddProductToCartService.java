package com.onlineshop.service.cart;

import com.onlineshop.domain.vo.CartDetailsRequest;
import com.onlineshop.repository.entities.CartDetails;

public interface AddProductToCartService {

	CartDetails addProductToCart(Long cartId, CartDetailsRequest request);

}
