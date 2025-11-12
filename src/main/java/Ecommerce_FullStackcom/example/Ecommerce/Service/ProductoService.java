package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categoria;
import Ecommerce_FullStackcom.example.Ecommerce.model.Producto;
import Ecommerce_FullStackcom.example.Ecommerce.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    public Producto obtenerPorId (Integer id){
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto actualizarTodo(Integer id, Producto producto){
        Producto existente = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombreProducto(producto.getNombreProducto());
        existente.setDescripcionProducto(producto.getDescripcionProducto());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setCategoria(producto.getCategoria());

        return productoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        productoRepository.delete(producto);
    }

    public Producto patchProducto(Integer id, Producto productoParcial){
        Producto existente = obtenerPorId(id);
        if (existente != null) {
            if (productoParcial.getNombreProducto() != null)
                existente.setNombreProducto(productoParcial.getNombreProducto());
            if (productoParcial.getDescripcionProducto() != null)
                existente.setDescripcionProducto(productoParcial.getDescripcionProducto());
            if (productoParcial.getPrecio() != null)
                existente.setPrecio(productoParcial.getPrecio());
            if (productoParcial.getStock() != null)
                existente.setStock(productoParcial.getStock());
            if (productoParcial.getCategoria() != null)
                existente.setCategoria(productoParcial.getCategoria());
            return guardar(existente);
        }
        return null;
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }
}
