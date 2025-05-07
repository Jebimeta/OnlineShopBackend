package com.onlineshop.util;

import com.onlineshop.config.properties.RitaRougeProperties;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
@Getter
@Setter
@AllArgsConstructor
public class ImageService {

	private RitaRougeProperties ritaRougeProperties;

	public String saveImageInDirectory(MultipartFile image) throws IOException {
		log.info("INIT - ImageService -> saveImageInDirectory()");
		Path uploadDirectoryImage = Paths.get(ritaRougeProperties.getUpload().getDirectory());
		String imageName = getValidImageName(image);
		if (!Files.exists(uploadDirectoryImage)) {
			Files.createDirectories(uploadDirectoryImage);
		}
		saveImage(image, uploadDirectoryImage.resolve(imageName));
		log.info("END - ImageService -> saveImageInDirectory() - The image was saved successfully");
		return imageName;
	}

	private String getValidImageName(MultipartFile image) {
		String imageName = image.getOriginalFilename();
		if (StringUtils.isEmpty(image.getOriginalFilename())) {
			throw new BusinessException(AppErrorCode.ERROR_IMAGE_NAME);
		}
		return imageName;
	}

	private void saveImage(MultipartFile image, Path destination) {
		try (InputStream inputStream = image.getInputStream()) {
			log.info("INIT - ImageService -> saveImage() - Saving image");
			Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException exception) {
			log.error("The image can not be saved successfully: {}", destination.getFileName());
			throw new BusinessException(AppErrorCode.ERROR_SAVE_IMAGE, exception);
		}
	}

	public void deleteImage(String imageName) {
		Path imagePath = Paths.get(ritaRougeProperties.getUpload().getDirectory(), imageName);

		try {
			Files.delete(imagePath);
		}
		catch (IOException exception) {
			log.error("The image can not be deleted successfully: {}", imageName);
			throw new BusinessException(AppErrorCode.ERROR_DELETE_IMAGE, exception);
		}
	}

}
