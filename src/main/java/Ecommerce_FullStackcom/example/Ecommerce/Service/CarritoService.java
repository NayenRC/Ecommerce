package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Carrito;
import Ecommerce_FullStackcom.example.Ecommerce.repository.CarritoRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listar() {
        return carritoRepository.findAll();
    }

    public Carrito obtenerPorId(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarTodo(Integer id, Carrito carrito) {
        Carrito existente = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        existente.setUsuario(carrito.getUsuario());
        existente.setFechaCreacion(carrito.getFechaCreacion());
        existente.setEstado(carrito.getEstado());
        existente.setMetodoPago(carrito.getMetodoPago());
        existente.setDetalleCarrito(carrito.getDetalleCarrito());
        existente.setTotal(carrito.getTotal());

        return carritoRepository.save(existente);
    }

    public Carrito patchCarrito(Integer id, Carrito parcial) {
        Carrito existente = obtenerPorId(id);
        if (existente != null) {

            if (parcial.getUsuario() != null) existente.setUsuario(parcial.getUsuario());
            if (parcial.getFechaCreacion() != null) existente.setFechaCreacion(parcial.getFechaCreacion());
            if (parcial.getEstado() != null) existente.setEstado(parcial.getEstado());
            if (parcial.getMetodoPago() != null) existente.setMetodoPago(parcial.getMetodoPago());
            if (parcial.getDetalleCarrito() != null) existente.setDetalleCarrito(parcial.getDetalleCarrito());
            if (parcial.getTotal() != null) existente.setTotal(parcial.getTotal());

            return guardar(existente);
        }
        return null;
    }

    public void eliminar(Integer id) {
        Carrito c = carritoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carritoRepository.delete(c);
    }
}

