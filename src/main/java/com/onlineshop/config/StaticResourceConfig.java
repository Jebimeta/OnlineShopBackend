package com.onlineshop.config;

import com.onlineshop.config.properties.OnlineShopProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Clase para configurar el manejo de recursos estáticos
@Configuration
@RequiredArgsConstructor
public class StaticResourceConfig implements WebMvcConfigurer {

	private final OnlineShopProperties properties;

	/**
	 * Configura los manejadores de recursos estáticos para servir imágenes de productos.
	 * @param registry el registro de manejadores de recursos
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app/static/products/images/**", "/products/images/**")
			.addResourceLocations("file:/app/static/products/images/", properties.getUpload().getLocalDirectory());
	}

}
