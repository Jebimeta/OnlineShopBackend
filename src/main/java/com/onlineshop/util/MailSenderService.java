package com.onlineshop.util;

import com.onlineshop.config.properties.OnlineShopProperties;
import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.exception.BusinessException;
import com.onlineshop.exception.enums.AppErrorCode;
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

// Service para enviar correos electrónicos relacionados con la tienda en línea
@Service
@RequiredArgsConstructor
@Log4j2
public class MailSenderService {

	private final JavaMailSender mailSender;

	private final OnlineShopProperties properties;

	private final CustomerQueryService customerQueryService;

	private final CustomerVerificationTokenService tokenService;

	private final CustomerUpdateService customerUpdateService;

	/**
	 * Envía un correo electrónico de verificación al usuario después de su registro.
	 * @param user El usuario al que se le enviará el correo de verificación.
	 * @throws BusinessException Si ocurre un error al enviar el correo electrónico.
	 */
	public void sendVerificationEmail(Customer user) throws BusinessException {
		String subject = "Verificación de Registro";
		String senderName = "Puntillismo Shop";
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

	/**
	 * Envía un correo electrónico al administrador cuando un usuario envía un mensaje a
	 * través del formulario de contacto.
	 * @param senderName El nombre del remitente.
	 * @param phoneNumber El número de teléfono del remitente.
	 * @param gender El género del remitente.
	 * @param emailMessage El mensaje enviado por el remitente.
	 * @return Un objeto EmailResponse que indica el resultado del envío del correo.
	 * @throws BusinessException Si ocurre un error al enviar el correo electrónico.
	 */
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

	/**
	 * Envía un correo electrónico de recuperación de contraseña al usuario.
	 * @param email El correo electrónico del usuario que solicita la recuperación de
	 * contraseña.
	 * @return Un objeto EmailResponse que indica el resultado del envío del correo.
	 * @throws BusinessException Si ocurre un error al enviar el correo electrónico.
	 */
	public EmailResponse sendPasswordRecoveryEmail(String email) {
		String subject = "Restablecer contraseña";
		String senderName = "Puntillismo Shop";

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

	/**
	 * Envía un correo electrónico con el contenido proporcionado al destinatario
	 * especificado.
	 * @param email El correo electrónico del destinatario.
	 * @param senderName El nombre del remitente que aparecerá en el correo.
	 * @param subject El asunto del correo electrónico.
	 * @param content El contenido del correo electrónico, que puede incluir HTML.
	 * @throws MessagingException Si ocurre un error al crear o enviar el mensaje.
	 * @throws UnsupportedEncodingException Si ocurre un error al codificar el nombre del
	 * remitente.
	 */
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
