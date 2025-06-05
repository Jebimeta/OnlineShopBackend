package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service que permite la búsqueda de un producto por su ID
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

	private final ProductJpaRepository productJpaRepository;

	/**
	 * Busca un producto en la base de datos por su ID.
	 *
	 * @param id El ID del producto a buscar.
	 * @return El producto encontrado.
	 */
	@Override
	public Product findProductById(Long id) {
		log.info("INIT - ProductQueryServiceImpl -> findProductById()");
		Optional<Product> optionalProduct = productJpaRepository.findById(id);
		log.info("END - ProductQueryServiceImpl -> findProductById()");
		return optionalProduct.orElseThrow();
	}

}
