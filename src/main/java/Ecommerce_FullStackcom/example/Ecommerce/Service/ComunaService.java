package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Comuna;
import Ecommerce_FullStackcom.example.Ecommerce.repository.ComunaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> listar() {
        return comunaRepository.findAll();
    }

    public Comuna obtenerPorId(Integer id) {
        return comunaRepository.findById(id).orElse(null);
    }

    public Comuna guardar(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna actualizarTodo(Integer id, Comuna comuna) {
        Comuna existente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        existente.setNombre_comuna(comuna.getNombre_comuna());
        existente.setRegion(comuna.getRegion());
        return comunaRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        comunaRepository.delete(comuna);
    }

    public Comuna patchComuna(Integer id, Comuna parcial) {
        Comuna existente = obtenerPorId(id);
        if (existente != null) {
            if (parcial.getNombre_comuna() != null)
                existente.setNombre_comuna(parcial.getNombre_comuna());
            if (parcial.getRegion() != null)
                existente.setRegion(parcial.getRegion());
            return guardar(existente);
        }
        return null;
    }
}
