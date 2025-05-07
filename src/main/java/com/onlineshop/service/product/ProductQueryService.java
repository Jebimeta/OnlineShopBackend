package com.onlineshop.service.product;

import com.onlineshop.repository.entities.Product;

public interface ProductQueryService {

	Product findProductById(Long id);

}
