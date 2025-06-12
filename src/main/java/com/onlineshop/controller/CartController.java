package com.onlineshop.controller;

import com.onlineshop.apifirst.api.CartApiDelegate;
import com.onlineshop.domain.vo.CartDetailsRequest;
import com.onlineshop.domain.vo.CartDetailsResponse;
import com.onlineshop.domain.vo.CartRequest;
import com.onlineshop.domain.vo.CartResponse;
import com.onlineshop.domain.vo.ProductResponse;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.entities.CartDetails;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.service.cart.*;
import com.onlineshop.service.cart.factory.CartFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Clase que implementa la interfaz CartApiDelegate para manejar las operaciones relacionadas con el carrito de compras.
@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController implements CartApiDelegate {

	private final ConversionService conversionService;

	private final CartFindAllService cartFindAllService;

	private final CartCreateService cartCreateService;

	private final CartQueryService cartQueryService;

	private final CartUpdateService cartUpdateService;

	private final CartDeleteService cartDeleteService;

	private final CartFactory cartFactory;

	private final AddProductToCartService addProductToCartService;

	private final RemoveProductFromCartService removeProductFromCartService;

	/**
	 * Agrega un producto al carrito.
	 * @param cartId ID del carrito al que se le agregará el producto.
	 * @param cartDetailsRequest Detalles del producto a agregar al carrito.
	 * @return Respuesta con los detalles del carrito actualizado.
	 */
	@Override
	public ResponseEntity<CartDetailsResponse> addCartProduct(Long cartId, CartDetailsRequest cartDetailsRequest) {
		log.info("INIT - CartController -> addCartProduct()");
		CartDetails cartDetails = addProductToCartService.addProductToCart(cartId, cartDetailsRequest);
		CartDetailsResponse cartDetailsResponse = conversionService.convert(cartDetails, CartDetailsResponse.class);
		log.info("END - CartController -> addCartProduct()");
		return ResponseEntity.ok(cartDetailsResponse);
	}

	/**
	 * Crea un nuevo carrito con los detalles del cliente y del carrito.
	 * @param cartRequest Detalles del carrito a crear.
	 * @return Respuesta con los detalles del carrito creado.
	 */
	@Override
	public ResponseEntity<CartResponse> createCart(CartRequest cartRequest) {
		log.info("INIT - CartController -> createCart()");
		Cart cart = cartFactory.createCartWithCustomerAndCartDetails(cartRequest);
		Cart createdCart = cartCreateService.createCartWithCartDetails(cart);
		CartResponse cartResponse = conversionService.convert(createdCart, CartResponse.class);
		log.info("END - CartController -> createCart()");
		return ResponseEntity.ok(cartResponse);
	}

	/**
	 * Obtiene un carrito por su ID.
	 * @param cartId ID del carrito a obtener.
	 * @return Respuesta con los detalles del carrito obtenido.
	 */
	@Override
	public ResponseEntity<CartResponse> getCart(Long cartId) {
		log.info("INIT - CartController -> getCart()");
		Cart cart = cartQueryService.findCartById(cartId);
		CartResponse obtainedCart = conversionService.convert(cart, CartResponse.class);
		log.info("END - CartController -> getCart()");
		return ResponseEntity.ok(obtainedCart);
	}

	/**
	 * Elimina un producto del carrito.
	 * @param cartId ID del carrito del cual se eliminará el producto.
	 * @param cartDetailsId ID del detalle del carrito que se eliminará.
	 * @return Respuesta con un mensaje de éxito o error.
	 */
	@Override
	public ResponseEntity<String> removeCartProduct(Long cartId, Long cartDetailsId) {
		log.info("INIT - CartController -> removeCartProduct()");
		log.info("END - CartController -> removeCartProduct()");
		return ResponseEntity.ok(removeProductFromCartService.removeProductFromCart(cartId, cartDetailsId));
	}

	/**
	 * Actualiza un carrito existente.
	 * @param cartId ID del carrito a actualizar.
	 * @param cartRequest Detalles del carrito a actualizar.
	 * @return Respuesta con los detalles del carrito actualizado.
	 */
	@Override
	public ResponseEntity<CartResponse> updateCart(Long cartId, CartRequest cartRequest) {
		log.info("INIT - CartController -> updateCart()");
		Cart cart = conversionService.convert(cartRequest, Cart.class);
		Cart updatedCart = cartUpdateService.updateCart(cartId, cart);
		CartResponse cartResponse = conversionService.convert(updatedCart, CartResponse.class);
		log.info("END - CartController -> updateCart()");
		return ResponseEntity.ok(cartResponse);

	}

	/**
	 * Elimina un carrito por su ID.
	 * @param cartId ID del carrito a eliminar.
	 * @return Respuesta con un mensaje de éxito o error.
	 */
	@Override
	public ResponseEntity<String> deleteCart(Long cartId) {
		log.info("INIT - CartController -> deleteCart()");
		log.info("END - CartController -> deleteCart()");
		return ResponseEntity.ok(cartDeleteService.deleteCartById(cartId));
	}

	/**
	 * Obtiene todos los carritos existentes.
	 * @return Respuesta con una lista de carritos.
	 */
	@Override
	public ResponseEntity<List<CartResponse>> getAllCarts() {
		log.info("INIT - CartController -> getAllCarts()");
		List<CartResponse> allCartsObtained = cartFindAllService.findAllCarts()
			.stream()
			.map(cart -> conversionService.convert(cart, CartResponse.class))
			.toList();
		log.info("END - CartController -> getAllCarts()");
		return ResponseEntity.ok(allCartsObtained);
	}

	/**
	 * Endpoint para obtener los productos del carrito de un usuario por su customerId.
	 * @param customerId ID del usuario (customer)
	 * @return Lista de productos en el carrito
	 */

	@Override
	public ResponseEntity<List<ProductResponse>> getProductsInCartByCustomerId(Long customerId) {
		log.info("GET /shop/cart/products/{}", customerId);
		List<Product> products = cartQueryService.findProductsInCartByCustomerId(customerId);
		List<ProductResponse> productResponses = products.stream()
			.map(product -> conversionService.convert(product, ProductResponse.class))
			.toList();
		return ResponseEntity.ok(productResponses);
	}

}
