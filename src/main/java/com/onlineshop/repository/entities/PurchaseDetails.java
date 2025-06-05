package com.onlineshop.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Clase CartDetails que representa los detalles de un carrito de compras en la base de datos
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "purchase_details")
public class PurchaseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Purchase purchase;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Float price;

	private Integer quantity;

}
