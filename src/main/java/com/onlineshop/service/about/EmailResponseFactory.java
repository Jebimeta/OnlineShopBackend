package com.onlineshop.service.about;

import com.onlineshop.domain.vo.EmailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// Factory que crea la respuesta del email
@Slf4j
@Component
public class EmailResponseFactory {

	private EmailResponseFactory() {
		log.info("You can not use EmailResponseFactory constructor because is private");
	}

	/**
	 * Crea una respuesta de email con el mensaje proporcionado.
	 *
	 * @param message El mensaje a incluir en la respuesta del email.
	 * @return Una instancia de EmailResponse con el mensaje proporcionado.
	 */
	public static EmailResponse createEmailResponse(String message) {
		EmailResponse emailResponse = new EmailResponse();
		emailResponse.setMessage(message);
		return emailResponse;
	}

}
