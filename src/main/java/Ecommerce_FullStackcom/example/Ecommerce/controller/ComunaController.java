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

import Ecommerce_FullStackcom.example.Ecommerce.Service.ComunaService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Comuna;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comunas")

public class ComunaController {

    @Autowired
    private ComunaService comunaServicio;

    @GetMapping
    @Operation(summary = "Obtiene todas las comunas", description = "Lista todas las comunas registradas")
    public ResponseEntity<List<Comuna>> listar() {
        List<Comuna> comunas = comunaServicio.listar();
        return comunas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(comunas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una comuna por ID", description = "Busca una comuna específica por su identificador")
    public ResponseEntity<Comuna> obtenerPorId(@PathVariable Integer id) {
        Comuna comuna = comunaServicio.obtenerPorId(id);
        return (comuna != null) ? ResponseEntity.ok(comuna) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea una comuna", description = "Registra una nueva comuna en la base de datos")
    public ResponseEntity<Comuna> crear(@Valid @RequestBody Comuna comuna) {
        Comuna creada = comunaServicio.guardar(comuna);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una comuna", description = "Actualiza completamente los datos de una comuna")
    public ResponseEntity<Comuna> actualizar(@PathVariable Integer id, @Valid @RequestBody Comuna comuna) {
        Comuna actualizada = comunaServicio.actualizarTodo(id, comuna);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita parcialmente una comuna", description = "Permite modificar parcialmente los datos de una comuna")
    public ResponseEntity<Comuna> patch(@PathVariable Integer id, @RequestBody Comuna comuna) {
        Comuna actualizada = comunaServicio.patchComuna(id, comuna);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una comuna", description = "Elimina una comuna existente por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Comuna comuna = comunaServicio.obtenerPorId(id);
        if (comuna == null)
            return ResponseEntity.notFound().build();
        comunaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
