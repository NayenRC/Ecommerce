package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categorias;
import Ecommerce_FullStackcom.example.Ecommerce.repository.CategoriasRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> listar() {
        return categoriasRepository.findAll();
    }

    public Categorias obtenerPorId(Integer id) {
        return categoriasRepository.findById(id).orElse(null);
    }

    public Categorias guardar(Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    public Categorias actualizarTodo(Integer id, Categorias categorias){
        Categorias existente = categoriasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categorias no encontradas"));

        existente.setNombreCategorias(categorias.getNombreCategorias());
        return categoriasRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Categorias categorias = categoriasRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Categorias no encontradas"));
        categoriasRepository.delete(categorias);
    }

    public Categorias patchCategorias(Integer id, Categorias categoriasParcial) {
        Categorias existente = obtenerPorId(id);
        if(existente != null){
            if(categoriasParcial.getNombreCategorias() != null)
                existente.setNombreCategorias(categoriasParcial.getNombreCategorias());
            return guardar(existente);
        }
        return null;
    }

    public List<Categorias> buscarPorNombre(String nombre){
        return categoriasRepository.findByNombreCategoriasContainingIgnoreCase(nombre);
    }
}
