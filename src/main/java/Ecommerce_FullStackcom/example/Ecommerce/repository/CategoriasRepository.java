package Ecommerce_FullStackcom.example.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

    List<Categorias> findByNombreCategoriasContainingIngoreCase(String nombreCategorias);
}
