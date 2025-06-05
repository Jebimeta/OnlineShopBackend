package com.onlineshop.service.auth.impl;

import com.onlineshop.repository.entities.Customer;
import com.onlineshop.repository.jpa.CustomerJpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

// Esta clase implementa UserDetailsService para cargar los detalles del usuario durante la autenticación
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final CustomerJpaRepository repository;

	/**
	 * Carga los detalles del usuario por su nombre de usuario.
	 *
	 * @param username El nombre de usuario del cliente.
	 * @return Un objeto UserDetails que contiene la información del usuario.
	 * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = repository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		List<GrantedAuthority> authorities = Collections
			.singletonList(new SimpleGrantedAuthority("ROLE_" + customer.getRol().name()));

		return new User(customer.getUsername(), customer.getPassword(), authorities);
	}

}
