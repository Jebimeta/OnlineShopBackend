package com.onlineshop.config.properties;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UploadProperties {

	private String directory;

	private String baseUrl;

	private String localDirectory;

}
