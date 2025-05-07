package com.onlineshop.repository.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private String size;

	private String type;

	private Float price;

	private String image;

	@OneToMany(mappedBy = "product")
	private List<PurchaseDetails> purchaseDetails;

	@OneToMany(mappedBy = "product")
	private List<CartDetails> cartDetails;

}
