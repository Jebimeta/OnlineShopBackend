package com.onlineshop.service.product;

import com.onlineshop.repository.entities.Product;

// Interface que permite la busqueda de un producto por su ID
public interface ProductQueryService {

	Product findProductById(Long id);

}
