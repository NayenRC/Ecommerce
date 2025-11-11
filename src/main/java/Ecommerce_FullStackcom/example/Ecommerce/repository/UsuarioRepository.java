package Ecommerce_FullStackcom.example.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
    // Ejemplo:
    // Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
