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

    @Autowired
    private UsuarioService usuarioService;

    // LISTAR
    public List<Carrito> listar() {
        return carritoRepository.findAll();
    }

    // OBTENER POR ID
    public Carrito obtenerPorId(Integer id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    // GUARDAR
    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    // ACTUALIZAR COMPLETO
    public Carrito actualizarTodo(Integer id, Carrito carrito) {
        Carrito existente = obtenerPorId(id);

        existente.setUsuario(carrito.getUsuario());
        existente.setTienda(carrito.getTienda());
        existente.setCantidad(carrito.getCantidad());

        return carritoRepository.save(existente);
    }

    // ELIMINAR
    public void eliminar(Integer id) {
        Carrito existente = obtenerPorId(id);
        carritoRepository.delete(existente);
    }

    // PATCH — actualiza solo campos enviados
    public Carrito patchCarrito(Integer id, Carrito carritoParcial) {
        Carrito existente = obtenerPorId(id);

        if (carritoParcial.getUsuario() != null)
            existente.setUsuario(carritoParcial.getUsuario());

        if (carritoParcial.getTienda() != null)
            existente.setTienda(carritoParcial.getTienda());

        if (carritoParcial.getCantidad() != null)
            existente.setCantidad(carritoParcial.getCantidad());

        return carritoRepository.save(existente);
    }
}

