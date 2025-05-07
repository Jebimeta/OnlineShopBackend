package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.PurchaseResponse;
import com.onlineshop.repository.entities.Purchase;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PurchaseResponseToPurchaseMapper extends Converter<PurchaseResponse, Purchase> {

	Purchase convert(PurchaseResponse source);

}
