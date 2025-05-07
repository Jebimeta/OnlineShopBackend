package com.onlineshop.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "order_details")
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
