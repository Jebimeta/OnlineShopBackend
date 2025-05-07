package com.onlineshop.service.cart.factory;

import com.onlineshop.domain.vo.CartDetailsRequest;
import com.onlineshop.domain.vo.CartRequest;
import com.onlineshop.domain.vo.CustomerResponse;
import com.onlineshop.repository.entities.Cart;
import com.onlineshop.repository.entities.CartDetails;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.service.auth.AuthenticationService;
import com.onlineshop.service.cart.impl.CartFindQueryServiceImpl;
import com.onlineshop.service.customer.CustomerQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CartFactory {

	private final CartFindQueryServiceImpl cartFindQueryServiceImpl;

	private final CustomerQueryService customerQueryService;

	private final AuthenticationService authenticationService;

	public Cart createCartWithCustomerAndCartDetails(CartRequest cartRequest) {
		CustomerResponse customerResponse = authenticationService.getAuthenticatedUser();
		Customer customer = customerQueryService.getCustomerByUserName(customerResponse.getUsername());

		Cart cart = new Cart();
		cart.setCustomer(customer);
		cart.setDate(LocalDateTime.now());

		List<CartDetails> cartDetailsList = new ArrayList<>();
		CartDetails cartDetails = new CartDetails();
		cartDetails.setCart(cart);

		for (CartDetailsRequest details : cartRequest.getCartDetails()) {
			Product product = cartFindQueryServiceImpl.findProductById(details);
			cartDetails.setProduct(product);
			cartDetails.setQuantity(details.getQuantity());
			cartDetails.setPrice(product.getPrice() * details.getQuantity());

			cartDetailsList.add(cartDetails);
		}
		cart.setCartDetails(cartDetailsList);

		return cart;
	}

}
