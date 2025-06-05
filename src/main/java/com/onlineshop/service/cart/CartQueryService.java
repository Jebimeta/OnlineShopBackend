package com.onlineshop.service.cart;

import com.onlineshop.repository.entities.Cart;

// Interface que define el servicio para crear un carrito con detalles
public interface CartQueryService {

	Cart findCartById(Long id);

}
