package com.onlineshop.service.cart;

import com.onlineshop.repository.entities.Cart;

public interface CartQueryService {

	Cart findCartById(Long id);

}
