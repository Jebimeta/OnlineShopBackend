package com.onlineshop.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Enum que trata los códigos de error.
@Getter
@RequiredArgsConstructor
public enum AppErrorCode {

	ERROR_UNEXPECTED("An unexpected error occurred."), ERROR_CREATE_CART("No cartDetails exist to create the cart."),
	ERROR_CART_NOT_FOUND("The cart could not be found."),
	ERROR_INVALID_CART_REQUEST("The cart update request is invalid."),
	ERROR_IMAGE_NAME("The file name is null or empty."), ERROR_SAVE_IMAGE("Failed to save the image."),
	ERROR_DELETE_IMAGE("Error while attempting to delete the image."),
	ERROR_PURCHASE_NOT_FOUND("Purchase can not be found."),
	ERROR_SEND_VERIFICATION_EMAIL("Failed to send verification email."),
	ERROR_SEND_EMAIL("The email can not be sent successfully."),
	ERROR_NOT_AUTHENTICATED("No authenticated user found."),
	ERROR_NOT_VERIFIED_ACCOUNT("User is not verified. Please check your email."),
	ERROR_USER_NOT_FOUND("User not found."), ERROR_BUILD_PRODUCT("The product can not be built"),
	ERROR_PRODUCT_NOT_FOUND("Product not found."), ERROR_INVALID_VERIFICATION_TOKEN("Invalid verification token."),
	ERROR_UNFORBIDDEN_CART("User not have permission to obtain this cart"),
	ERROR_UNFORBIDDEN_PURCHASE("User not have permission to obtain this purchase");

	private final String message;

}
