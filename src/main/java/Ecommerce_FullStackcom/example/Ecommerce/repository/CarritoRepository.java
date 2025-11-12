package Ecommerce_FullStackcom.example.Ecommerce.repository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Carrito;
import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    List<Carrito> findByUsuario(Usuario usuario);
}
