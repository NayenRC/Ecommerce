package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Estado;
import Ecommerce_FullStackcom.example.Ecommerce.repository.EstadoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado obtenerPorId(Integer id) {
        return estadoRepository.findById(id).orElse(null);
    }

    public Estado guardar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado actualizarTodo(Integer id, Estado estado) {
        Estado existente = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        existente.setNombreEstado(estado.getNombreEstado());

        return estadoRepository.save(existente);
    }

    public Estado patchEstado(Integer id, Estado parcial) {
        Estado existente = obtenerPorId(id);
        if (existente != null) {

            if (parcial.getNombreEstado() != null)
                existente.setNombreEstado(parcial.getNombreEstado());

            return guardar(existente);
        }
        return null;
    }

    public void eliminar(Integer id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        estadoRepository.delete(estado);
    }
}