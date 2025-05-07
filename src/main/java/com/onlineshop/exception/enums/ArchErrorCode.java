package com.onlineshop.exception.enums;

import com.onlineshop.exception.handler.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArchErrorCode implements ErrorCode {

	TECH_001("HTTP message is not readable"), TECH_002("Method arguments are not valid"),
	TECH_003("Missing servlet request part"), TECH_004("Missing servlet request parameter"), TECH_005("Type mismatch"),
	TECH_006("No such element exception"), TECH_007("HTTP request method not supported"),
	TECH_008("HTTP media type not supported"), TECH_009("Internal server error"),
	TECH_010("Invalid hostname for server"), TECH_011("Problems creating, querying or manipulating date-time objects"),
	TECH_012("Timeout on a socket read or accept operation"), TECH_013("Constraint violation");

	private final String reasonPhrase;

}
