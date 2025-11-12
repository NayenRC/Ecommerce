package Ecommerce_FullStackcom.example.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.MetodoPago;
import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    List<MetodoPago> findByUsuario(Usuario usuario);
}
