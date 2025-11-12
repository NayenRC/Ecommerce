package Ecommerce_FullStackcom.example.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ecommerce_FullStackcom.example.Ecommerce.model.Tienda;
import Ecommerce_FullStackcom.example.Ecommerce.repository.TiendaRepository;

import java.util.List;

@Service
public class TiendaService {
    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Tienda> getAllTiendas() {
        return tiendaRepository.findAll();
    }

    public Tienda getTiendaById(Long id) {
        return tiendaRepository.findById(id).orElse(null);
    }

    public Tienda saveTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public void deleteTienda(Long id) {
        tiendaRepository.deleteById(id);
    }

}
