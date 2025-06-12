package com.onlineshop.controller;

import com.onlineshop.apifirst.api.ProductsApiDelegate;
import com.onlineshop.domain.vo.ProductResponse;
import com.onlineshop.repository.ProductFactory;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.service.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

// Clase que implementa la interfaz ProductsApiDelegate para manejar las operaciones relacionadas con los productos.
@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductsApiDelegate {

	private final ConversionService conversionService;

	private final ProductsFindAllService productsFindAllService;

	private final ProductCreateService productCreateService;

	private final ProductQueryService productQueryService;

	private final ProductUpdateService productUpdateService;

	private final ProductDeleteService productDeleteService;

	private final ProductFactory productFactory;

	/**
	 * Obtiene todos los productos.
	 * @return Respuesta con la lista de productos.
	 */
	@Override
	public ResponseEntity<List<ProductResponse>> getProducts() {
		log.info("INIT - ProductController -> getProducts()");
		List<ProductResponse> productsResponse = productsFindAllService.findAllProducts()
			.stream()
			.map(product -> conversionService.convert(product, ProductResponse.class))
			.toList();
		log.info("END - ProductController -> getProducts()");
		return ResponseEntity.ok(productsResponse);
	}

	/**
	 * Crea un nuevo producto.
	 * @param name Nombre del producto.
	 * @param description Descripción del producto.
	 * @param size Tamaño del producto.
	 * @param type Tipo de producto.
	 * @param price Precio del producto.
	 * @param image Imagen del producto.
	 * @return Respuesta con los detalles del producto creado.
	 */
	@Override
	public ResponseEntity<ProductResponse> createProduct(String name, String description, String size, String type,
			BigDecimal price, MultipartFile image) {
		log.info("INIT - ProductController -> createProduct()");
		try {
			Product product = productFactory.buildProduct(name, description, size, type, price, image);
			Product createdProduct = productCreateService.createProduct(product);
			ProductResponse productResponse = conversionService.convert(createdProduct, ProductResponse.class);
			log.info("END - ProductController -> createProduct()");
			return ResponseEntity.ok(productResponse);
		}
		catch (IOException e) {
			log.info("EXCEPTION - ProductController -> createProduct()");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ProductResponse());
		}

	}

	/**
	 * Obtiene un producto por su ID.
	 * @param productId ID del producto a obtener.
	 * @return Respuesta con los detalles del producto.
	 */
	@Override
	public ResponseEntity<ProductResponse> getProductById(Long productId) {
		log.info("INIT - ProductController -> getProductById()");
		Product product = productQueryService.findProductById(productId);
		ProductResponse productResponse = conversionService.convert(product, ProductResponse.class);
		log.info("END - ProductController -> getProductById()");
		return ResponseEntity.ok(productResponse);
	}

	/**
	 * Elimina un producto por su ID.
	 * @param productId ID del producto a eliminar.
	 * @return Respuesta vacía indicando que la operación fue exitosa.
	 */
	@Override
	public ResponseEntity<Void> deleteProductById(Long productId) {
		log.info("INIT - ProductController -> deleteProductById()");
		productDeleteService.deleteProductById(productId);
		log.info("END - ProductController -> deleteProductById()");
		return ResponseEntity.ok(null);
	}

	/**
	 * Actualiza un producto existente.
	 * @param productId ID del producto a actualizar.
	 * @param name Nombre del producto.
	 * @param description Descripción del producto.
	 * @param size Tamaño del producto.
	 * @param type Tipo de producto.
	 * @param price Precio del producto.
	 * @param image Imagen del producto.
	 * @return Respuesta con los detalles del producto actualizado.
	 */
	@Override
	public ResponseEntity<ProductResponse> updateProduct(Long productId, String name, String description, String size,
			String type, BigDecimal price, MultipartFile image) {
		log.info("INIT - ProductController -> updateProduct()");
		try {
			Product product = productFactory.buildProduct(name, description, size, type, price, image);
			Product updatedProduct = productUpdateService.updateProduct(productId, product);
			ProductResponse productResponse = conversionService.convert(updatedProduct, ProductResponse.class);
			log.info("END - ProductController -> updateProduct()");
			return ResponseEntity.ok(productResponse);
		}
		catch (IOException e) {
			log.info("EXCEPTION - ProductController -> updateProduct()");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ProductResponse());
		}

	}

}
