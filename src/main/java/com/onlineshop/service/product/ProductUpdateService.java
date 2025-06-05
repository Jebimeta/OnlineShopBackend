package com.onlineshop.service.product;

import com.onlineshop.repository.entities.Product;

// Interface que actualiza un producto
public interface ProductUpdateService {

	Product updateProduct(Long id, Product product);

}
