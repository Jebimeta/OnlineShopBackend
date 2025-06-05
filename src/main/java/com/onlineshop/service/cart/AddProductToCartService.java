package com.onlineshop.service.cart;

import com.onlineshop.domain.vo.CartDetailsRequest;
import com.onlineshop.repository.entities.CartDetails;

// Interface que define el servicio para agregar un producto al carrito
public interface AddProductToCartService {

	CartDetails addProductToCart(Long cartId, CartDetailsRequest request);

}
