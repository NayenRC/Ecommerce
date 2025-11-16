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
import org.springframework.web.bind.annotation.RequestBody;
import Ecommerce_FullStackcom.example.Ecommerce.Service.CategoriasService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categorias;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    @Operation(summary = "Lista todas las categorías", description = "Obtiene todas las categorías existentes")
    public ResponseEntity<List<Categorias>> listar() {
        List<Categorias> categorias = categoriasService.listar();
        return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una categoría padre por ID", description = "Busca una categoría por su identificador")
    public ResponseEntity<Categorias> obtenerPorId(@PathVariable Integer id) {
        Categorias categoria = categoriasService.obtenerPorId(id);
        return (categoria != null) ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea una categoría", description = "Registra una nueva categoría")
    public ResponseEntity<Categorias> crear(@Valid @RequestBody Categorias categoria) {
        Categorias creada = categoriasService.guardar(categoria);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoría ", description = "Actualiza los datos de una categoría")
    public ResponseEntity<Categorias> actualizar(@PathVariable Integer id, @Valid @RequestBody Categorias categoria) {
        Categorias actualizada = categoriasService.actualizarTodo(id, categoria);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita parcialmente una categoría ", description = "Modifica parcialmente los datos de una categoría")
    public ResponseEntity<Categorias> patch(@PathVariable Integer id, @RequestBody Categorias categoria) {
        Categorias actualizada = categoriasService.patchCategorias(id, categoria);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una categoría", description = "Elimina una categoría por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Categorias categoria = categoriasService.obtenerPorId(id);
        if (categoria == null)
            return ResponseEntity.notFound().build();
        categoriasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}