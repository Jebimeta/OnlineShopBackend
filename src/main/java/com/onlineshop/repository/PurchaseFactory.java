package com.onlineshop.repository;

import com.onlineshop.domain.vo.PurchaseRequest;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.entities.PurchaseDetails;
import com.onlineshop.repository.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseFactory {

	private PurchaseFactory() {
		// Constructor privado, no se deben de crear objetos vacíos desde esta clase.
	}

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

	private static float calculateTotalAmount(List<PurchaseDetails> purchaseDetailsList) {
		float totalAmount = 0;
		for (PurchaseDetails details : purchaseDetailsList) {
			totalAmount += details.getPrice() * details.getQuantity();
		}
		return totalAmount;
	}

}
