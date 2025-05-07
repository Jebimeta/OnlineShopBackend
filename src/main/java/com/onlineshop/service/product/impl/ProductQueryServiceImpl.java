package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

	private final ProductJpaRepository productJpaRepository;

	@Override
	public Product findProductById(Long id) {
		log.info("INIT - ProductQueryServiceImpl -> findProductById()");
		Optional<Product> optionalProduct = productJpaRepository.findById(id);
		log.info("END - ProductQueryServiceImpl -> findProductById()");
		return optionalProduct.orElseThrow();
	}

}
