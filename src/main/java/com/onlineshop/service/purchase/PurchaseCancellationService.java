package com.onlineshop.service.purchase;

import com.onlineshop.repository.entities.Purchase;

public interface PurchaseCancellationService {

	Purchase cancelPurchaseById(Long id);

	Purchase cancelPurchaseConfirmationById(Long id);

}
