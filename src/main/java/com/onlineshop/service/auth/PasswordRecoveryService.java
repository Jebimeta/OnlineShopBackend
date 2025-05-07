package com.onlineshop.service.auth;

import com.onlineshop.domain.vo.EmailResponse;
import com.onlineshop.domain.vo.PasswordResetConfirmRequest;
import com.onlineshop.domain.vo.PasswordResetRequest;
import com.onlineshop.repository.entities.Customer;

public interface PasswordRecoveryService {

	public EmailResponse sendEmailToRecoveryPassword(PasswordResetRequest passwordResetRequest);

	public Customer confirmRecoveryPassword(PasswordResetConfirmRequest passwordResetConfirmRequest);

}
