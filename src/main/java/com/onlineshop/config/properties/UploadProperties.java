package com.onlineshop.config.properties;

import lombok.*;
import org.springframework.stereotype.Component;

// Clase para almacenar las propiedades de carga de archivos.
@Getter
@Setter
@Component
public class UploadProperties {

	private String directory;

	private String baseUrl;

	private String localDirectory;

}
