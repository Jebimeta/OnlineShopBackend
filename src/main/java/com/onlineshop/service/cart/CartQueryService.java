package com.onlineshop.service.cart;

import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.entities.Product;
import java.util.List;

// Interface que define el servicio para crear un carrito con detalles
public interface CartQueryService {

	Cart findCartById(Long id);

	List<Product> findProductsInCartByCustomerId(Long customerId);

}
