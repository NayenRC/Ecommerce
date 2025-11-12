package Ecommerce_FullStackcom.example.Ecommerce.repository;

import org.springframework.stereotype.Repository;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNombreCategoriaContainingIngoreCase(String nombreCategoria);
}
