package com.onlineshop.repository.entities;

import com.onlineshop.repository.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(name = "order_date", nullable = false)
	private LocalDateTime purchaseDate;

	@Column(name = "total_amount", nullable = false)
	private Float totalAmount;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@Column(name = "shipping_address", nullable = false)
	private String shippingAddress;

	@OneToMany(mappedBy = "purchase")
	private List<PurchaseDetails> purchaseDetails;

}
