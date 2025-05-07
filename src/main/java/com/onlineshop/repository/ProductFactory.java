package com.onlineshop.repository;

import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Product;
import com.onlineshop.util.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductFactory {

	private final ImageService imageService;

	public Product buildProduct(String name, String description, String size, String type, BigDecimal price,
			MultipartFile image) throws IOException {
		try {
			String imagePath = imageService.saveImageInDirectory(image);
			return Product.builder()
				.name(name)
				.description(description)
				.size(size)
				.type(type)
				.price(price.setScale(2, RoundingMode.HALF_UP).floatValue())
				.image(imagePath)
				.build();
		}
		catch (IOException e) {
			log.error(String.valueOf(AppErrorCode.ERROR_BUILD_PRODUCT), e);
			throw new BusinessException(AppErrorCode.ERROR_BUILD_PRODUCT, e);
		}

	}

}
