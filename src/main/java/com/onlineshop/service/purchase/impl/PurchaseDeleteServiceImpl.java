package com.onlineshop.service.purchase.impl;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.jpa.PurchaseJpaRepository;
import com.onlineshop.service.purchase.PurchaseDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service que implementa la logica de negocio para eliminar compras
@Service
@RequiredArgsConstructor
public class PurchaseDeleteServiceImpl implements PurchaseDeleteService {

	private final PurchaseJpaRepository purchaseJpaRepository;

	/*
	 * Metodo que elimina una compra por su ID Busca la compra por su ID, si existe la
	 * elimina y devuelve un mensaje de éxito Si no existe, lanza una excepción de negocio
	 */
	@Override
	public String deletePurchaseById(Long orderId) {
		Optional<Purchase> optionalPurchase = purchaseJpaRepository.findById(orderId);
		if (optionalPurchase.isPresent()) {
			purchaseJpaRepository.delete(optionalPurchase.get());
			return "Purchase has been deleted successfully";
		}
		throw new BusinessException(AppErrorCode.ERROR_PURCHASE_NOT_FOUND);
	}

}
