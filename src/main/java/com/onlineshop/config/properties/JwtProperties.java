package com.onlineshop.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class JwtProperties {

	private String secretKey;

	private String accessTokenExpiration;

	private String refreshTokenExpiration;

}
