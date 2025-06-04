package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.CartResponse;
import com.onlineshop.repository.entities.Cart;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

// Mapper que convierte un Cart a CartResponse
@Mapper(componentModel = "spring", uses = { CartDetailsToCartDetailsResponseMapper.class })
public interface CartToCartResponseMapper extends Converter<Cart, CartResponse> {

	@Mapping(source = "id", target = "userId")
	CartResponse convert(@NonNull Cart source);

}
