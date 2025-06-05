package com.onlineshop.service.purchase;

import com.onlineshop.domain.vo.PurchaseRequest;
import com.onlineshop.repository.entities.Purchase;

// Interface que define los metodos para crear compras
public interface PurchaseCreateService {

	Purchase makePurchase(PurchaseRequest purchase);

}
