package com.onlineshop.controller;

import com.onlineshop.apifirst.api.AboutApiDelegate;
import com.onlineshop.domain.vo.EmailRequest;
import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.service.about.AboutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

// Clase que implementa la logica de negocio para el envío de correos electrónicos.
@Slf4j
@RestController
@RequiredArgsConstructor
public class AboutController implements AboutApiDelegate {

	private final AboutService aboutService;

	/**
	 * Método que envía un correo electrónico.
	 * @param request Objeto que contiene la información del correo electrónico a enviar.
	 * @return Respuesta con el estado del envío del correo electrónico.
	 */
	@Override
	public ResponseEntity<EmailResponse> sendEmail(EmailRequest request) {
		log.info("INIT - AboutController -> sendEmail()");
		log.info("END - AboutController -> sendEmail()");
		return ResponseEntity.ok(aboutService.sendEmail(request));
	}

}
