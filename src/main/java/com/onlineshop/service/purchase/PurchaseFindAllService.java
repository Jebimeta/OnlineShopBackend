package com.onlineshop.service.purchase;

import com.onlineshop.repository.entities.Purchase;

import java.util.List;

public interface PurchaseFindAllService {

	List<Purchase> findAllPurchases();

}
