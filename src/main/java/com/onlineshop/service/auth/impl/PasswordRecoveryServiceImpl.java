package com.onlineshop.service.auth.impl;

import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.domain.vo.PasswordResetConfirmRequest;
import com.onlineshop.domain.vo.PasswordResetRequest;
import com.onlineshop.repository.entities.Customer;
import com.onlineshop.service.auth.PasswordRecoveryService;
import com.onlineshop.service.customer.CustomerUpdateService;
import com.onlineshop.service.customer.CustomerVerificationTokenService;
import com.onlineshop.util.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

	private final CustomerUpdateService customerUpdateService;

	private final MailSenderService mailSenderService;

	private final CustomerVerificationTokenService customerVerificationTokenService;

	@Override
	public EmailResponse sendEmailToRecoveryPassword(PasswordResetRequest passwordResetRequest) {
		return mailSenderService.sendPasswordRecoveryEmail(passwordResetRequest.getEmail());
	}

	@Override
	public Customer confirmRecoveryPassword(PasswordResetConfirmRequest passwordResetConfirmRequest) {
		Customer customer = customerVerificationTokenService
			.findCustomerByVerificationToken(passwordResetConfirmRequest.getVerificationToken());

		customer.setPassword(passwordResetConfirmRequest.getNewPassword());
		customerUpdateService.updateCustomer(customer);

		return customer;
	}

}
