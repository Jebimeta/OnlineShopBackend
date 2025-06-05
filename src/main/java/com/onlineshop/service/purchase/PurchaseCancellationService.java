package com.onlineshop.service.purchase;

import com.onlineshop.repository.entities.Purchase;

// Interface que define los metodos para cancelar compras
public interface PurchaseCancellationService {

	Purchase cancelPurchaseById(Long id);

	Purchase cancelPurchaseConfirmationById(Long id);

}
