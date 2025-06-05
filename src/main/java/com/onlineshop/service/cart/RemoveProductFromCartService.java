package com.onlineshop.service.cart;

// Interface que define el servicio para eliminar un carrito por su ID
public interface RemoveProductFromCartService {

	String removeProductFromCart(Long cartId, Long cartDetailsId);

}
