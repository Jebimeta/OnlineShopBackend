package com.onlineshop.controller.mapper;

import com.onlineshop.domain.vo.ProductRequest;
import com.onlineshop.repository.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.core.convert.converter.Converter;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

// Mapper que convierte un ProductRequest a Product
@Mapper(componentModel = "spring")
public interface ProductRequestToProductMapper extends Converter<ProductRequest, Product> {

	@Mapping(source = "image", target = "image", qualifiedByName = "mapImage")
	Product convert(ProductRequest source);

	/**
	 * Maps de la imagen del producto desde un recurso a una ruta de archivo.
	 * Este método guarda la imagen en un directorio específico y devuelve la ruta del archivo.
	 * @param imageResource
	 * @return
	 * @throws IOException
	 */
	@Named(value = "mapImage")
	default String mapImage(Resource imageResource) throws IOException {
		File image = imageResource.getFile();
		String uploadDirectory = "src/main/java/resources/static/products/images";

		File uploadDirectoryFile = new File(uploadDirectory);
		if (!uploadDirectoryFile.exists()) {
			uploadDirectoryFile.mkdirs();
		}
		String imagePath = uploadDirectory + System.currentTimeMillis() + ".jpg";
		File destination = new File(imagePath);
		FileUtils.copyFile(image, destination);
		return imagePath;

	}

}
