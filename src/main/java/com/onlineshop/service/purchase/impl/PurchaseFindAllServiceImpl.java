package com.onlineshop.service.purchase.impl;

import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.jpa.PurchaseJpaRepository;
import com.onlineshop.service.purchase.PurchaseFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseFindAllServiceImpl implements PurchaseFindAllService {

	private final PurchaseJpaRepository purchaseJpaRepository;

	@Override
	public List<Purchase> findAllPurchases() {
		return purchaseJpaRepository.findAll();
	}

}
