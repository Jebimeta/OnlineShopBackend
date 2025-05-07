package com.onlineshop.service.cart;

import com.onlineshop.repository.entities.Cart;

public interface CartCreateService {

	Cart createCartWithCartDetails(Cart cart);

}
