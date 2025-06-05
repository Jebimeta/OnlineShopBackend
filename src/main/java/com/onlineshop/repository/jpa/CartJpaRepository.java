package com.onlineshop.repository.jpa;

import com.onlineshop.repository.entities.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// CartJpaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Cart
@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

	// Método para eliminar un producto específico del carrito
	// Utiliza una consulta JPQL para eliminar un CartDetails basado en el ID del carrito y el ID del detalle del carrito
	// @Modifying indica que esta consulta modificará la base de datos
	@Modifying
	@Transactional
	@Query("DELETE FROM CartDetails cd WHERE cd.cart.id = :cartId AND cd.id = :cartDetailsId")
	void deleteProductFromCart(@Param("cartId") Long cartId, @Param("cartDetailsId") Long cartDetailsId);

}
