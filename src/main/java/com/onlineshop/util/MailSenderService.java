package com.onlineshop.util;

import com.onlineshop.config.properties.RitaRougeProperties;
import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.service.about.EmailResponseFactory;
import com.onlineshop.service.customer.CustomerQueryService;
import com.onlineshop.service.customer.CustomerUpdateService;
import com.onlineshop.service.customer.CustomerVerificationTokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailSenderService {

	private final JavaMailSender mailSender;

	private final RitaRougeProperties properties;

	private final CustomerQueryService customerQueryService;

	private final CustomerVerificationTokenService tokenService;

	private final CustomerUpdateService customerUpdateService;

	public void sendVerificationEmail(Customer user) throws BusinessException {
		String subject = "Verificación de Registro";
		String senderName = "Rita Rouge";
		String verifyURL = properties.getMail().getHost() + "/auth/verify/" + user.getVerificationToken();

		String content = "Estimado " + user.getName() + ",<br>"
				+ "Por favor, haga clic en el siguiente enlace para verificar su registro:<br>" + "<h3><a href=\""
				+ verifyURL + "\" target=\"_self\">VERIFICAR</a></h3>" + "Gracias,<br>" + senderName + ".";

		try {
			sendMessage(user.getEmail(), senderName, subject, content);
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			log.error(e);
			throw new BusinessException(AppErrorCode.ERROR_SEND_VERIFICATION_EMAIL);
		}

	}

	public EmailResponse receiveContactUs(String senderName, String phoneNumber, String gender, String emailMessage)
			throws BusinessException {

		String subject = "Contacta con nosotros";
		String email = properties.getMail().getHostEmail();
		String headerMessage = "Se ha recibido un correo a través del formulario de contacta con nosotros, "
				+ "la información es la siguiente: ";
		String content = headerMessage + "<br><br>" + "Usuario: " + senderName + "<br>" + "Número de teléfono: "
				+ phoneNumber + "<br>" + "Género: " + gender + "<br>" + "Mensaje: " + emailMessage;

		try {
			sendMessage(email, senderName, subject, content);
			return EmailResponseFactory.createEmailResponse("The email was sent successfully");
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			log.error(e);
			throw new BusinessException(AppErrorCode.ERROR_SEND_EMAIL);
		}
	}

	public EmailResponse sendPasswordRecoveryEmail(String email) {
		String subject = "Restablecer contraseña";
		String senderName = "Rita Rouge";

		Customer customer = customerQueryService.getCustomerByUserName(email);

		String verificationToken = tokenService.generateVerificationToken(customer);
		customer.setVerificationToken(verificationToken);

		String verifyURL = properties.getMail().getHost() + "/auth/password-reset/request/"
				+ customer.getVerificationToken();

		String content = "Estimado " + customer.getName() + ",<br>"
				+ "Por favor, haga clic en el siguiente enlace para asignar una nueva contraseña:<br>"
				+ "<h3><a href=\"" + verifyURL + "\" target=\"_self\">CAMBIAR CONTRASEÑA</a></h3>" + "Gracias,<br>"
				+ senderName + ".";

		try {
			sendMessage(customer.getEmail(), senderName, subject, content);
			customerUpdateService.updateCustomer(customer);
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			log.error(e);
			throw new BusinessException(AppErrorCode.ERROR_SEND_VERIFICATION_EMAIL);
		}
		return new EmailResponse("Se ha creado el correo correctamente.");
	}

	private void sendMessage(String email, String senderName, String subject, String content)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(properties.getMail().getUsername(), senderName);
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(content, true);

		mailSender.send(message);
	}

}
