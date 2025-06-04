package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.LoginRequest;
import com.onlineshop.repository.entities.Customer;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

// Mapper que convierte un LoginRequest a Customer
@Mapper(componentModel = "spring")
public interface LoginRequestToCustomerMapper extends Converter<LoginRequest, Customer> {

	Customer convert(@NonNull LoginRequest source);

}
