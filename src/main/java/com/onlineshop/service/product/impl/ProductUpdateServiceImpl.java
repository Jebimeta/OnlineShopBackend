package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

	private final ProductJpaRepository productJpaRepository;

	@Override
	public Product updateProduct(Long id, Product product) {
		log.info("INIT - ProductUpdateServiceImpl -> updateProduct()");
		product.setId(id);
		Product requestProduct = productJpaRepository.getReferenceById(id);
		if (product.getId().equals(requestProduct.getId())) {
			Product updatedProduct = productJpaRepository.save(product);
			log.info("END - ProductUpdateServiceImpl -> updateProduct()");
			return updatedProduct;
		}
		return null;
	}

}
