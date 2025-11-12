package Ecommerce_FullStackcom.example.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Estado;
import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    List<Estado> findByUsuario(Usuario usuario);
}
