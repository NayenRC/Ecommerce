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
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import Ecommerce_FullStackcom.example.Ecommerce.Service.CategoriaService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Lista todas las categorías", description = "obtiene todas las categorías existentes")
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.listar();
        return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una categoría por ID", description = "Busca una categoría específica por su identificador")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        return (categoria != null) ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea una categoría", description = "Registra una nueva categorías")
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        Categoria creada = categoriaService.guardar(categoria);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoría", description = "Actualiza los datos de una categoría")
    public ResponseEntity<Categoria> actualizar(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        Categoria actualizada = categoriaService.actualizarTodo(id, categoria);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita parcialmente una categoría", description = "Modifica parcialmente los datos de una categoría")
    public ResponseEntity<Categoria> patch(@PathVariable Integer id, @RequestBody Categoria categoria) {
        Categoria actualizada = categoriaService.patchCategoria(id, categoria);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una categoría", description = "Elimina una categoría por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null)
            return ResponseEntity.notFound().build();
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}