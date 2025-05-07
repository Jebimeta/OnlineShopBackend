package com.onlineshop.config.enums;

import lombok.Getter;

@Getter
public enum OnlineShopApiEndpoints {

	// Swagger and Docs URLs
	SWAGGER_UI_HTML("/swagger-ui.html"), SWAGGER_UI("/swagger-ui/**"), API_DOCS("/v3/api-docs/**"),

	// Base URLs
	BASE_AUTH_URL("/api/v1/auth"), BASE_SHOP_URL("/api/v1/shop"), BASE_USERS_URL("/api/v1/users"),

	// Auth Endpoints
	LOGIN_URL(BASE_AUTH_URL.getUrl() + "/login"), REGISTER_URL(BASE_AUTH_URL.getUrl() + "/register"),
	REFRESH_TOKEN_URL(BASE_AUTH_URL.getUrl() + "/refresh-token"),
	VERIFY_TOKEN_URL(BASE_AUTH_URL.getUrl() + "/verify/{token}"),
	AUTHENTICATED_USER_INFO_URL(BASE_AUTH_URL.getUrl() + "/authenticated-user"),

	// Shop Endpoints
	PRODUCTS_LIST_URL(BASE_SHOP_URL.getUrl() + "/products/**"),
	OBTAIN_PRODUCT_BY_ID_URL(BASE_SHOP_URL.getUrl() + "/product/{productId}"),
	CREATE_PRODUCT_URL(BASE_SHOP_URL.getUrl() + "/create-product"),
	UPDATE_PRODUCT_URL(BASE_SHOP_URL.getUrl() + "/update-product/{productId}"),
	DELETE_PRODUCT_URL(BASE_SHOP_URL.getUrl() + "/product/delete-product/{productId}"),

	// Cart Endpoints
	OBTAIN_ALL_CARTS_URL(BASE_SHOP_URL.getUrl() + "/carts"), CREATE_CART_URL(BASE_SHOP_URL.getUrl() + "/create-cart"),
	DELETE_CART_URL(BASE_SHOP_URL.getUrl() + "/cart/delete-cart/{cartId}"),
	ADD_PRODUCT_URL(BASE_SHOP_URL.getUrl() + "/cart/{cartId}/add-product"),
	REMOVE_PRODUCT_FROM_CART_URL(BASE_SHOP_URL.getUrl() + "/cart/{cartId}/remove-product/{cartDetailsId}"),
	OBTAIN_CART_URL(BASE_SHOP_URL.getUrl() + "/cart/{cartId}"),

	// Purchase Endpoints
	OBTAIN_ALL_PURCHASES_URL(BASE_SHOP_URL.getUrl() + "/purchases"),
	OBTAIN_PURCHASE_BY_ID_URL(BASE_SHOP_URL.getUrl() + "/find-purchase/{purchaseId}"),
	MAKE_PURCHASE_URL(BASE_SHOP_URL.getUrl() + "/new-purchase"),
	REQUEST_PURCHASE_CANCELLATION(BASE_SHOP_URL.getUrl() + "/purchase-cancellation-pending/{purchaseId}"),
	VALIDATION_PURCHASE_CANCELLATION(BASE_SHOP_URL.getUrl() + "/purchase-cancellation-validation/{purchaseId}"),

	// Users Endpoints
	USERS_LIST_URL(BASE_USERS_URL.getUrl()), OBTAIN_USER_BY_ID_URL(BASE_USERS_URL.getUrl() + "/{username}"),
	DELETE_USER_URL(BASE_USERS_URL.getUrl() + "/delete/{username}"),
	UPDATE_USER_URL(BASE_USERS_URL.getUrl() + "/update-user"),

	// Utils Endpoints
	IMAGES_URL("/products/images/**"), SEND_EMAIL_URL("/api/v1/about/send-email");

	private final String url;

	OnlineShopApiEndpoints(String url) {
		this.url = url;
	}

}
