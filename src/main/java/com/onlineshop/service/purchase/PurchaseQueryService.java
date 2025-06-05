package com.onlineshop.service.purchase;

import com.onlineshop.repository.entities.Purchase;

// Interface que define el metodo para consultar una compra por su ID
public interface PurchaseQueryService {

	Purchase findPurchaseById(Long orderId);

}
