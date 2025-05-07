package com.onlineshop.service.product;

import com.onlineshop.repository.entities.Product;

public interface ProductUpdateService {

	Product updateProduct(Long id, Product product);

}
