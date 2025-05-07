package com.onlineshop.config;

import com.onlineshop.config.properties.RitaRougeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class StaticResourceConfig implements WebMvcConfigurer {

	private final RitaRougeProperties properties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app/static/products/images/**", "/products/images/**")
			.addResourceLocations("file:/app/static/products/images/", properties.getUpload().getLocalDirectory());
	}

}
