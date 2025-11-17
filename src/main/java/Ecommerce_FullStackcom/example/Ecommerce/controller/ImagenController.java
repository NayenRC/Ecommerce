package Ecommerce_FullStackcom.example.Ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecommerce_FullStackcom.example.Ecommerce.Service.ImagenService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Imagen;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping
    @Operation(summary = "Lista todas las imágenes", description = "Obtiene todas las imágenes registradas")
    public ResponseEntity<List<Imagen>> listar() {
        List<Imagen> imagenes = imagenService.listar();
        return imagenes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(imagenes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una imagen por ID", description = "Busca una imagen específica por su ID")
    public ResponseEntity<Imagen> obtener(@PathVariable Integer id) {
        Imagen imagen = imagenService.obtenerPorId(id);
        return imagen != null
                ? ResponseEntity.ok(imagen)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea una nueva imagen", description = "Registra una nueva imagen asociada a un producto")
    public ResponseEntity<Imagen> crear(@Valid @RequestBody Imagen imagen) {
        Imagen creada = imagenService.guardar(imagen);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza completamente una imagen", description = "Reemplaza todos los datos de la imagen")
    public ResponseEntity<Imagen> actualizar(@PathVariable Integer id, @Valid @RequestBody Imagen imagen) {
        Imagen actualizada = imagenService.actualizarTodo(id, imagen);
        return actualizada != null
                ? ResponseEntity.ok(actualizada)
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza parcialmente una imagen", description = "Permite modificar solo campos específicos")
    public ResponseEntity<Imagen> patch(@PathVariable Integer id, @RequestBody Imagen parcial) {
        Imagen actualizada = imagenService.patchImagen(id, parcial);
        return actualizada != null
                ? ResponseEntity.ok(actualizada)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una imagen", description = "Borra una imagen según su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Imagen img = imagenService.obtenerPorId(id);
        if (img == null)
            return ResponseEntity.notFound().build();

        imagenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
