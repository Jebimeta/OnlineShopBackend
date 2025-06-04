package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.CustomerResponse;
import com.onlineshop.repository.entities.Customer;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

// Mapper que convierte un Customer a CustomerResponse
@Mapper(componentModel = "spring")
public interface CustomerToCustomerResponseMapper extends Converter<Customer, CustomerResponse> {

	CustomerResponse convert(Customer source);

}
