package com.onlineshop.service.purchase.impl;

import com.onlineshop.exception.BusinessException;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.repository.entities.Purchase;
import com.onlineshop.repository.jpa.PurchaseJpaRepository;
import com.onlineshop.service.auth.AuthenticationService;
import com.onlineshop.service.purchase.PurchaseQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// Service que implementa la logica de negocio para consultar compras
@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseQueryServiceImpl implements PurchaseQueryService {

	private final PurchaseJpaRepository purchaseJpaRepository;

	private final AuthenticationService authenticationService;

	/* Metodo que obtiene una compra por su ID
	 * Busca la compra por su ID, si existe valida que pertenezca al cliente autenticado
	 * Si no existe, lanza una excepción de negocio
	 */
	@Override
	@Transactional
	public Purchase findPurchaseById(Long purchaseId) {
		Purchase purchase = purchaseJpaRepository.findById(purchaseId)
			.orElseThrow(() -> new BusinessException(AppErrorCode.ERROR_CART_NOT_FOUND));
		return checkPurchaseInCustomerPurchase(purchase);
	}

	/* Metodo que obtiene todas las compras del cliente autenticado
	 * Obtiene las compras del cliente autenticado y las devuelve
	 */
	private Purchase checkPurchaseInCustomerPurchase(Purchase purchase) {
		List<Purchase> customerPurchases = getCustomerPurchases();
		validatePurchaseInCustomerPurchases(purchase, customerPurchases);
		return purchase;
	}

	/* Metodo que obtiene todas las compras del cliente autenticado
	 * Obtiene las compras del cliente autenticado y las devuelve
	 */
	private List<Purchase> getCustomerPurchases() {
		List<Purchase> customerPurchases = authenticationService.findUserByTokenAccess().getPurchases();
		return customerPurchases != null ? customerPurchases : Collections.emptyList();
	}

	/* Metodo que valida que la compra pertenezca al cliente autenticado
	 * Si la compra no pertenece al cliente, lanza una excepción de negocio
	 */
	private void validatePurchaseInCustomerPurchases(Purchase purchase, List<Purchase> customerPurchases) {
		if (!customerPurchases.contains(purchase)) {
			log.error(AppErrorCode.ERROR_UNFORBIDDEN_PURCHASE.getMessage());
			throw new BusinessException(AppErrorCode.ERROR_UNFORBIDDEN_PURCHASE);
		}
	}

}
