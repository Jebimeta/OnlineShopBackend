package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.CartDetailsResponse;
import com.onlineshop.repository.entities.CartDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

// Mapper que convierte CartDetails a CartDetailsResponse
@Mapper(componentModel = "spring")
public interface CartDetailsToCartDetailsResponseMapper extends Converter<CartDetails, CartDetailsResponse> {

	@Mapping(source = "product.id", target = "productId")
	CartDetailsResponse convert(CartDetails source);

}
