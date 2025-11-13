package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Despacho;
import Ecommerce_FullStackcom.example.Ecommerce.repository.DespachoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DespachoService {

    @Autowired
    private DespachoRepository despachoRepository;

    public List<Despacho> listar() {
        return despachoRepository.findAll();
    }

    public Despacho obtenerPorId(Integer id) {
        return despachoRepository.findById(id).orElse(null);
    }

    public Despacho guardar(Despacho despacho) {
        return despachoRepository.save(despacho);
    }

    public Despacho actualizarTodo(Integer id, Despacho despacho) {
        Despacho existente = despachoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));

        existente.setCarrito(despacho.getCarrito());
        existente.setDireccionEnvio(despacho.getDireccionEnvio());
        existente.setFechaEnvio(despacho.getFechaEnvio());
        existente.setFechaEntrega(despacho.getFechaEntrega());
        existente.setEstadoDespacho(despacho.getEstadoDespacho());

        return despachoRepository.save(existente);
    }

    public Despacho patchDespacho(Integer id, Despacho parcial) {
        Despacho existente = obtenerPorId(id);
        if (existente != null) {

            if (parcial.getCarrito() != null)
                existente.setCarrito(parcial.getCarrito());
            if (parcial.getDireccionEnvio() != null)
                existente.setDireccionEnvio(parcial.getDireccionEnvio());
            if (parcial.getFechaEnvio() != null)
                existente.setFechaEnvio(parcial.getFechaEnvio());
            if (parcial.getFechaEntrega() != null)
                existente.setFechaEntrega(parcial.getFechaEntrega());
            if (parcial.getEstadoDespacho() != null)
                existente.setEstadoDespacho(parcial.getEstadoDespacho());

            return guardar(existente);
        }
        return null;
    }

    public void eliminar(Integer id) {
        Despacho despacho = despachoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));
        despachoRepository.delete(despacho);
    }
}
