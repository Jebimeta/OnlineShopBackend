package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

	private final ProductJpaRepository productJpaRepository;

	@Override
	public Product createProduct(Product product) {
		log.info("INIT - ProductCreateServiceImpl -> createProduct()");
		Product createdProduct = productJpaRepository.save(product);
		log.info("END - ProductCreateServiceImpl -> createProduct() - Product: {}", createdProduct.getName());
		return createdProduct;
	}

}
