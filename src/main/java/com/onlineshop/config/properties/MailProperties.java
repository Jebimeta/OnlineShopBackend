package com.onlineshop.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

// Clase para almacenar las propiedades de MailSender
@Getter
@Setter
@Component
public class MailProperties {

	private String hostEmail;

	private String host;

	private String username;

	private String password;

}
