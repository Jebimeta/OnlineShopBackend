package com.onlineshop.service.token.impl;

import com.onlineshop.exception.BusinessException;
import com.onlineshop.exception.enums.AppErrorCode;
import com.onlineshop.repository.entities.Token;
import com.onlineshop.repository.jpa.TokenJpaRepository;
import com.onlineshop.service.token.TokenDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenDeleteServiceImpl implements TokenDeleteService {

	private final TokenJpaRepository tokenJpaRepository;

	@Override
	public String deleteTokenById(Long tokenId) {
		Optional<Token> optionalToken = tokenJpaRepository.findById(tokenId);
		if (optionalToken.isPresent()) {
			tokenJpaRepository.delete(optionalToken.get());
			return "Token has been deleted succesfully";
		}
		throw new BusinessException(AppErrorCode.ERROR_TOKEN_NOT_FOUND);
	}

}
