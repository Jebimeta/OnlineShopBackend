package com.onlineshop.service.purchase;

import com.onlineshop.repository.entities.Purchase;

import java.util.List;

// Interface que define los metodos para obtener todas las compras
public interface PurchaseFindAllService {

	List<Purchase> findAllPurchases();

}
