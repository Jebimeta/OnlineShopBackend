package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// Service que permite la creacion de un producto
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

	private final ProductJpaRepository productJpaRepository;

	/**
	 * Crea un nuevo producto en la base de datos.
	 * @param product El producto a crear.
	 * @return El producto creado.
	 */
	@Override
	public Product createProduct(Product product) {
		log.info("INIT - ProductCreateServiceImpl -> createProduct()");
		Product createdProduct = productJpaRepository.save(product);
		log.info("END - ProductCreateServiceImpl -> createProduct() - Product: {}", createdProduct.getName());
		return createdProduct;
	}

}
