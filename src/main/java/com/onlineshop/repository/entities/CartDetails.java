package com.onlineshop.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Clase CartDetails que representa los detalles de un carrito de compras en la base de datos
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "cart_details")
public class CartDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Float price;

	private Integer quantity;

}
