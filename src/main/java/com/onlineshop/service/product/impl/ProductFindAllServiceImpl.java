package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductsFindAllService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductFindAllServiceImpl implements ProductsFindAllService {

	private final ProductJpaRepository productJpaRepository;

	@Override
	public List<Product> findAllProducts() {
		log.info("INIT - ProductFindAllServiceImpl -> findAllProducts()");
		List<Product> obtainedProducts = productJpaRepository.findAll();
		for (Product product : obtainedProducts) {
			log.debug("Product - {}", product.getName());
		}
		log.info("END - ProductFindAllServiceImpl -> findAllProducts()");
		return obtainedProducts;
	}

}
