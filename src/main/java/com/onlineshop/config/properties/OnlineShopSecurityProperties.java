package com.onlineshop.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

// Clase para almacenar las propiedades de seguridad.
@Getter
@Setter
@Component
@AllArgsConstructor
public class OnlineShopSecurityProperties {

	private JwtProperties jwt;

}
