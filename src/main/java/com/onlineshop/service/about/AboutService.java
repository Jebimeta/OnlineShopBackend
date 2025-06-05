package com.onlineshop.service.about;

import com.onlineshop.domain.vo.EmailRequest;
import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.util.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// Esta clase se encarga de enviar un email de contacto al administrador del sitio web.
@Service
@Slf4j
@RequiredArgsConstructor
public class AboutService {

	private final MailSenderService mailSenderService;

	/**
	 * Envia un email de contacto al administrador del sitio web.
	 *
	 * @param request Contiene los detalles del email a enviar.
	 * @return Respuesta del envío del email.
	 * @param request
	 * @return
	 */
	public EmailResponse sendEmail(EmailRequest request) {
		try {
			log.info("Sending contact us email");
			return mailSenderService.receiveContactUs(request.getSenderName(), request.getPhoneNumber(),
					request.getGender(), request.getEmailMessage());
		}
		catch (BusinessException exception) {
			log.error(String.valueOf(AppErrorCode.ERROR_SEND_EMAIL), exception.toString());
			return EmailResponseFactory.createEmailResponse(AppErrorCode.ERROR_SEND_EMAIL.getMessage());
		}
	}

}
