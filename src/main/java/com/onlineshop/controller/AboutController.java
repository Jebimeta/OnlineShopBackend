package com.onlineshop.controller;

import com.onlineshop.apifirst.api.AboutApiDelegate;
import com.onlineshop.domain.vo.EmailRequest;
import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.service.about.AboutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AboutController implements AboutApiDelegate {

	private final AboutService aboutService;

	@Override
	public ResponseEntity<EmailResponse> sendEmail(EmailRequest request) {
		log.info("INIT - AboutController -> sendEmail()");
		log.info("END - AboutController -> sendEmail()");
		return ResponseEntity.ok(aboutService.sendEmail(request));
	}

}
