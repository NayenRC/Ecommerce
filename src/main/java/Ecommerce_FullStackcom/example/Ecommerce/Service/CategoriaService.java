package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categoria;
import Ecommerce_FullStackcom.example.Ecommerce.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria guardar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    
    public Categoria actualizarTodo(Integer id, Categoria categoria) {
        Categoria existente = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        existente.setNombreCategoria(categoria.getNombreCategoria());
        existente.setCategorias(categoria.getCategorias());
        return categoriaRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        categoriaRepository.delete(categoria);
    }

    public Categoria patchCategoria(Integer id, Categoria categoriaParcial) {
        Categoria existente = obtenerPorId(id);
        if (existente != null) {
            if (categoriaParcial.getNombreCategoria() != null)
                existente.setNombreCategoria(categoriaParcial.getNombreCategoria());
            if (categoriaParcial.getCategorias() != null)
                existente.setCategorias(categoriaParcial.getCategorias());
            return guardar(existente);
        }
        return null;
    }

    public List<Categoria> buscarPorNombre(String nombre){
        return categoriaRepository.findByNombreCategoriaContainingIgnoreCase(nombre);
    }
}
