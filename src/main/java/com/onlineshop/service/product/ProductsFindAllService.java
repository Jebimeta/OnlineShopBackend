package com.onlineshop.service.product;

import com.onlineshop.repository.entities.Product;

import java.util.List;

// Interface que permite la búsqueda de una lista de productos
public interface ProductsFindAllService {

	List<Product> findAllProducts();

}
