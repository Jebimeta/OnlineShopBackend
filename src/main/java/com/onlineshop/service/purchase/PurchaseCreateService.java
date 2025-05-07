package com.onlineshop.service.purchase;

import com.onlineshop.domain.vo.PurchaseRequest;
import com.onlineshop.repository.entities.Purchase;

public interface PurchaseCreateService {

	Purchase makePurchase(PurchaseRequest purchase);

}
