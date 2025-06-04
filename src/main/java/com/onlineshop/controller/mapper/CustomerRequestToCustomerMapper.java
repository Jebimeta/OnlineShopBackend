package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.CustomerRequest;
import com.onlineshop.repository.entities.Customer;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

// Mapper que convierte CustomerRequest a Customer
@Mapper(componentModel = "spring")
public interface CustomerRequestToCustomerMapper extends Converter<CustomerRequest, Customer> {

	Customer convert(@NonNull CustomerRequest source);

}
