package Ecommerce_FullStackcom.example.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    // Ejemplo de método personalizado:
    // Optional<Rol> findByNombreRol(String nombreRol);
}
