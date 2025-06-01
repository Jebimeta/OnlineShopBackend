package com.onlineshop.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineshop.repository.enums.RolEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "customer")
public class Customer implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	private String password;

	private String name;

	private String surname;

	private String surname2;

	private String address;

	private String city;

	private String province;

	private String region;

	private String postalCode;

	private String email;

	private String phone;

	private Boolean status;

	@OneToMany(mappedBy = "customer")
	private List<Cart> cart;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Purchase> purchases;

	private String verificationToken;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Token> tokens;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RolEnum rol;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(rol.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
