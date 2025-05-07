package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.ProductResponse;
import com.onlineshop.repository.entities.Product;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ProductToProductResponseMapper extends Converter<Product, ProductResponse> {

	String BASE_URL = "http://localhost:8080/products/images/";

	@Mapping(source = "image", target = "image", qualifiedByName = "imageNameToImageUrl")
	ProductResponse convert(@NonNull Product source);

	@Named(value = "imageNameToImageUrl")
	default String imageNameToImageUrl(String imageName) {
		return BASE_URL + imageName;
	}

}
