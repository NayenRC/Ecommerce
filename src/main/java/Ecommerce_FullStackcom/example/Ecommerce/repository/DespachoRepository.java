package Ecommerce_FullStackcom.example.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Despacho;
import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;

@Repository
public interface DespachoRepository  extends JpaRepository<Despacho, Integer> {
    List<Despacho> findByUsuario(Usuario usuario);
}
