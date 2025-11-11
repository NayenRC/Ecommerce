package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Region;
import Ecommerce_FullStackcom.example.Ecommerce.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> listar() {
        return regionRepository.findAll();
    }

    public Region obtenerPorId(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region guardar(Region region) {
        return regionRepository.save(region);
    }

    public Region actualizarTodo(Integer id, Region region) {
        Region existente = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        existente.setNombre_region(region.getNombre_region());
        return regionRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        regionRepository.delete(region);
    }

    public Region patchRegion(Integer id, Region parcial) {
        Region existente = obtenerPorId(id);
        if (existente != null) {
            if (parcial.getNombre_region() != null)
                existente.setNombre_region(parcial.getNombre_region());
            return guardar(existente);
        }
        return null;
    }
}
