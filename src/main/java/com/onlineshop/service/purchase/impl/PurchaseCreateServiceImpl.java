package com.onlineshop.service.purchase.impl;

import com.onlineshop.domain.vo.CustomerResponse;
import com.onlineshop.domain.vo.PurchaseRequest;
import com.onlineshop.repository.PurchaseFactory;
import com.onlineshop.repository.entities.*;
import com.onlineshop.repository.jpa.PurchaseJpaRepository;
import com.onlineshop.service.auth.AuthenticationService;
import com.onlineshop.service.customer.CustomerQueryService;
import com.onlineshop.service.purchase.PurchaseCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service que implementa la logica de negocio para crear compras
@Service
@RequiredArgsConstructor
public class PurchaseCreateServiceImpl implements PurchaseCreateService {

	private final PurchaseJpaRepository purchaseJpaRepository;

	private final CustomerQueryService customerQueryService;

	private final AuthenticationService authenticationService;

	/*
	 * Metodo que crea una compra Obtiene el cliente autenticado, su ultimo carrito y crea
	 * una lista de detalles de compra Finalmente guarda la compra y la devuelve
	 */
	@Override
	@Transactional
	public Purchase makePurchase(PurchaseRequest purchaseRequest) {
		CustomerResponse customerResponse = authenticationService.getAuthenticatedUser();
		Customer customer = customerQueryService.getCustomerByUserName(customerResponse.getUsername());

		Cart cart = getCustomerLatestCart(customer);

		List<PurchaseDetails> purchaseDetailsList = createPurchaseDetailsList(cart);

		return savePurchase(purchaseRequest, customer, purchaseDetailsList);
	}

	/*
	 * Método que obtiene el último carrito del cliente Devuelve el último carrito de la
	 * lista de carritos del cliente
	 */
	private Cart getCustomerLatestCart(Customer customer) {
		return customer.getCart().get(customer.getCart().size() - 1);
	}

	/*
	 * Método que crea una lista de detalles de compra a partir del carrito Recorre los
	 * detalles del carrito y crea una lista de PurchaseDetails Devuelve la lista de
	 * PurchaseDetails
	 */
	private List<PurchaseDetails> createPurchaseDetailsList(Cart cart) {
		List<PurchaseDetails> purchaseDetailsList = new ArrayList<>();
		for (CartDetails cartDetails : cart.getCartDetails()) {
			PurchaseDetails purchaseDetails = new PurchaseDetails();
			purchaseDetails.setId(cartDetails.getId());
			purchaseDetails.setProduct(cartDetails.getProduct());
			purchaseDetails.setPrice(cartDetails.getPrice());
			purchaseDetails.setQuantity(cartDetails.getQuantity());
			purchaseDetailsList.add(purchaseDetails);
		}
		return purchaseDetailsList;
	}

	/*
	 * Método que guarda la compra en la base de datos Utiliza el PurchaseFactory para
	 * crear una entidad Purchase a partir de la solicitud de compra, el cliente y la
	 * lista de detalles de compra Devuelve la compra guardada
	 */
	private Purchase savePurchase(PurchaseRequest purchaseRequest, Customer customer,
			List<PurchaseDetails> purchaseDetailsList) {
		return purchaseJpaRepository
			.save(PurchaseFactory.createPurchaseFromPurchaseRequest(purchaseRequest, customer, purchaseDetailsList));
	}

}
