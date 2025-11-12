package Ecommerce_FullStackcom.example.Ecommerce.repository;

import org.springframework.stereotype.Repository;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categoria;
import Ecommerce_FullStackcom.example.Ecommerce.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);

    // aquí buscamos el producto por categoría omg
    List<Producto> findByCategoria(Categoria categoria);
}
