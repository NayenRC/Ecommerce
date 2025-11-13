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

import Ecommerce_FullStackcom.example.Ecommerce.Service.EstadoService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Estado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    @Operation(summary = "Lista todos los estados", description = "Devuelve todos los estados disponibles (activo, pagado, enviado...)")
    public ResponseEntity<List<Estado>> listar() {
        List<Estado> estados = estadoService.listar();
        return estados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un estado por ID", description = "Busca un estado específico")
    public ResponseEntity<Estado> obtener(@PathVariable Integer id) {
        Estado estado = estadoService.obtenerPorId(id);
        return estado != null ? ResponseEntity.ok(estado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo estado", description = "Registra un nuevo estado posible")
    public ResponseEntity<Estado> crear(@Valid @RequestBody Estado estado) {
        Estado creado = estadoService.guardar(estado);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un estado completamente", description = "Reemplaza todos los datos del estado")
    public ResponseEntity<Estado> actualizar(@PathVariable Integer id, @Valid @RequestBody Estado estado) {
        Estado actualizado = estadoService.actualizarTodo(id, estado);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza parcialmente un estado", description = "Modifica solo campos enviados")
    public ResponseEntity<Estado> patch(@PathVariable Integer id, @RequestBody Estado parcial) {
        Estado actualizado = estadoService.patchEstado(id, parcial);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un estado", description = "Borra un estado según su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Estado estado = estadoService.obtenerPorId(id);
        if (estado == null)
            return ResponseEntity.notFound().build();
        estadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
