package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.PurchaseResponse;
import com.onlineshop.repository.entities.Purchase;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

// Mapper que convierte un PurchaseResponse a Purchase
@Mapper(componentModel = "spring")
public interface PurchaseToPurchaseResponseMapper extends Converter<Purchase, PurchaseResponse> {

	PurchaseResponse convert(Purchase source);

}
