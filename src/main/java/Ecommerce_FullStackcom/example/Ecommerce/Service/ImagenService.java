package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Imagen;
import Ecommerce_FullStackcom.example.Ecommerce.repository.ImagenRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> listar() {
        return imagenRepository.findAll();
    }

    public Imagen obtenerPorId(Integer id) {
        return imagenRepository.findById(id).orElse(null);
    }

    public Imagen guardar(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public Imagen actualizarTodo(Integer id, Imagen imagen) {
        Imagen existente = imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        existente.setProducto(imagen.getProducto());
        existente.setUrlImagen(imagen.getUrlImagen());
        existente.setDescripcion(imagen.getDescripcion());

        return imagenRepository.save(existente);
    }

    public Imagen patchImagen(Integer id, Imagen parcial) {
        Imagen existente = obtenerPorId(id);
        if (existente != null) {

            if (parcial.getProducto() != null)
                existente.setProducto(parcial.getProducto());
            if (parcial.getUrlImagen() != null)
                existente.setUrlImagen(parcial.getUrlImagen());
            if (parcial.getDescripcion() != null)
                existente.setDescripcion(parcial.getDescripcion());

            return guardar(existente);
        }
        return null;
    }

    public void eliminar(Integer id) {
        Imagen img = imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        imagenRepository.delete(img);
    }
}
