package com.onlineshop.service.product.impl;

import com.onlineshop.repository.entities.Product;
import com.onlineshop.repository.jpa.ProductJpaRepository;
import com.onlineshop.service.product.ProductDeleteService;
import com.onlineshop.util.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service que permite la eliminación de un producto
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDeleteServiceImpl implements ProductDeleteService {

	private final ProductJpaRepository productJpaRepository;

	private final ImageService imageService;

	/**
	 * Elimina un producto de la base de datos por su ID.
	 * @param id El ID del producto a eliminar.
	 * @return Void
	 */
	@Override
	public Void deleteProductById(Long id) {
		log.info("INIT - ProductDeleteServiceImpl -> deleteProductById()");

		Optional<Product> product = productJpaRepository.findById(id);
		if (product.isPresent()) {
			imageService.deleteImage(product.get().getImage());
			productJpaRepository.delete(product.get());
			log.info("END - ProductDeleteServiceImpl -> deleteProductById() - Product deleted");
		}

		return null;
	}

}
