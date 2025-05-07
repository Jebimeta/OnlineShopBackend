package com.onlineshop.service.purchase.impl;

import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.enums.StatusEnum;
import com.onlineshop.service.purchase.PurchaseCancellationService;
import com.onlineshop.service.purchase.PurchaseQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseCancellationServiceImpl implements PurchaseCancellationService {

	private final PurchaseQueryService purchaseQueryService;

	@Override
	public Purchase cancelPurchaseById(Long id) {
		Purchase obtainedPurchase = purchaseQueryService.findPurchaseById(id);
		changeStatus(obtainedPurchase, StatusEnum.CANCELLATION_PENDING);
		return obtainedPurchase;
	}

	@Override
	public Purchase cancelPurchaseConfirmationById(Long id) {
		Purchase obtainedPurchase = purchaseQueryService.findPurchaseById(id);
		changeStatus(obtainedPurchase, StatusEnum.CANCELLED);
		return obtainedPurchase;
	}

	private void changeStatus(Purchase purchase, StatusEnum status) {
		purchase.setStatus(status);
	}

}
