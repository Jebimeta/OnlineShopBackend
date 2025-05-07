package com.onlineshop.service.purchase;

import com.onlineshop.repository.entities.Purchase;

public interface PurchaseQueryService {

	Purchase findPurchaseById(Long orderId);

}
