package com.onlineshop.service.product;

import com.onlineshop.repository.entities.Product;

// Interface que permite la creacion de un producto
public interface ProductCreateService {

	Product createProduct(Product product);

}
