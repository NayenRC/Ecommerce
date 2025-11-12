package Ecommerce_FullStackcom.example.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Producto;
import Ecommerce_FullStackcom.example.Ecommerce.model.ProductoCarrito;

@Repository
public interface ProductoCarritoRepository extends JpaRepository<ProductoCarrito, Integer> {

    List<ProductoCarrito> findByProducto(Producto producto);

    /*cuando este listo el carrito
    List<ProductoCarrito> findbyCarrito(Carrito carrito);
    así se listan los productos de un carrito*/ 
}
