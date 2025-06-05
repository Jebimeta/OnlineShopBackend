package com.onlineshop.service.purchase.impl;

import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.jpa.PurchaseJpaRepository;
import com.onlineshop.service.purchase.PurchaseFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Service que implementa la logica de negocio para obtener todas las compras
@Service
@RequiredArgsConstructor
public class PurchaseFindAllServiceImpl implements PurchaseFindAllService {

	private final PurchaseJpaRepository purchaseJpaRepository;

	/* Metodo que obtiene todas las compras
	 * Utiliza el repositorio JPA para obtener todas las compras y las devuelve
	 */
	@Override
	public List<Purchase> findAllPurchases() {
		return purchaseJpaRepository.findAll();
	}

}
