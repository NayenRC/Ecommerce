package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ecommerce_FullStackcom.example.Ecommerce.model.Producto;
import Ecommerce_FullStackcom.example.Ecommerce.model.ProductoCarrito;
import Ecommerce_FullStackcom.example.Ecommerce.repository.ProductoCarritoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoCarritoService {

    @Autowired
    public ProductoCarritoRepository productoCarritoRepository;

    public List<ProductoCarrito> listar(){
        return productoCarritoRepository.findAll();
    }

    public ProductoCarrito obtenerPorId(Integer id) {
        return productoCarritoRepository.findById(id).orElse(null);
    }

    public ProductoCarrito guardar(ProductoCarrito productoCarrito) {
        return productoCarritoRepository.save(productoCarrito);
    }

    public ProductoCarrito actualizarTodo(Integer id, ProductoCarrito productoCarrito) {
        ProductoCarrito existente = productoCarritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto de carrito no encontrado"));

        existente.setCantidadProducto(productoCarrito.getCantidadProducto());
        existente.setSubtotal(productoCarrito.getSubtotal());
        existente.setProducto(productoCarrito.getProducto());
        /*existente.setCarrito(productoCarrito.getCarrito()); esto para cuando Carrito esté listo*/ 

        return productoCarritoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        ProductoCarrito producarito = productoCarritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto de carrito no encontrado"));
        productoCarritoRepository.delete(producarito);
    }

    public ProductoCarrito patchProductoCarrito(Integer id, ProductoCarrito parcial) {
        ProductoCarrito existente = obtenerPorId(id);
        if (existente != null) {
            if (parcial.getCantidadProducto() != null)
                existente.setCantidadProducto(parcial.getCantidadProducto());
            if (parcial.getSubtotal() != null)
                existente.setSubtotal(parcial.getSubtotal());
            if (parcial.getProducto() != null)
                existente.setProducto(parcial.getProducto());
        /* if (parcial.getCarrito() != null)
                existente.setCarrito(parcial.getCarrito()); lo mismo de antes jeje*/

            return guardar(existente);
        }
        return null;
    }

    public List<ProductoCarrito> buscarPorProducto(Producto producto) {
        return productoCarritoRepository.findByProducto(producto);
    }
}
