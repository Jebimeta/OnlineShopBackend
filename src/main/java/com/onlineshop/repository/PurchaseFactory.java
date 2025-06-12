package com.onlineshop.repository;

import com.onlineshop.domain.vo.PurchaseRequest;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.entities.PurchaseDetails;
import com.onlineshop.repository.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

// Factory para crear objetos de tipo Purchase
public class PurchaseFactory {

	// Evita la creación de instancias de PurchaseFactory, ya que es una clase utilitaria.
	private PurchaseFactory() {
		// Constructor privado, no se deben de crear objetos vacíos desde esta clase.
		// Se utiliza para evitar la instanciación de esta clase.
	}

	// Método para crear una instancia de Purchase a partir de un PurchaseRequest,
	// Customer y una lista de PurchaseDetails
	public static Purchase createPurchaseFromPurchaseRequest(PurchaseRequest purchaseRequest, Customer customer,
			List<PurchaseDetails> purchaseDetailsList) {
		return Purchase.builder()
			.customer(customer)
			.status(StatusEnum.PLACED)
			.purchaseDate(LocalDateTime.now())
			.purchaseDetails(purchaseDetailsList)
			.totalAmount(calculateTotalAmount(purchaseDetailsList))
			.shippingAddress(purchaseRequest.getShippingAddress())
			.build();
	}

	// Método privado para calcular el monto total de la compra a partir de una lista de
	// PurchaseDetails
	private static float calculateTotalAmount(List<PurchaseDetails> purchaseDetailsList) {
		float totalAmount = 0;
		for (PurchaseDetails details : purchaseDetailsList) {
			totalAmount += details.getPrice() * details.getQuantity();
		}
		return totalAmount;
	}

}
