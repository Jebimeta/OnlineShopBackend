package com.onlineshop.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Clase Token que representa un token de acceso y actualización en la base de datos
@Getter
@Setter
@Entity
@Table(name = "token")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "access_token")
	private String accessToken;

	@Column(name = "refresh_token")
	private String refreshToken;

	@Column(name = "is_logged_out")
	private boolean loggedOut;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
