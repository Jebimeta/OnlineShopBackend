package com.onlineshop.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// Clase que representa las propiedades de configuración de la tienda en línea
@Getter
@Setter
@Component
@AllArgsConstructor
@ConfigurationProperties(prefix = "ritarouge")
public class OnlineShopProperties {

	private final UploadProperties upload;

	private final OnlineShopSecurityProperties security;

	private final MailProperties mail;

}
