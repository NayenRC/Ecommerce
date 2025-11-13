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

import Ecommerce_FullStackcom.example.Ecommerce.Service.DespachoService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Despacho;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/despachos")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @GetMapping
    @Operation(summary = "Lista todos los despachos", description = "Retorna la información de todos los despachos")
    public ResponseEntity<List<Despacho>> listar() {
        List<Despacho> lista = despachoService.listar();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un despacho por ID", description = "Busca un despacho específico")
    public ResponseEntity<Despacho> obtener(@PathVariable Integer id) {
        Despacho despacho = despachoService.obtenerPorId(id);
        return despacho != null ? ResponseEntity.ok(despacho) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo despacho", description = "Registra la información de un despacho asociado a un carrito")
    public ResponseEntity<Despacho> crear(@Valid @RequestBody Despacho despacho) {
        Despacho creado = despachoService.guardar(despacho);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un despacho completamente", description = "Reemplaza todos los datos del despacho")
    public ResponseEntity<Despacho> actualizar(@PathVariable Integer id, @Valid @RequestBody Despacho despacho) {
        Despacho actualizado = despachoService.actualizarTodo(id, despacho);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualización parcial de un despacho", description = "Modifica solo campos enviados")
    public ResponseEntity<Despacho> patch(@PathVariable Integer id, @RequestBody Despacho parcial) {
        Despacho actualizado = despachoService.patchDespacho(id, parcial);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un despacho", description = "Borra un despacho según su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Despacho despacho = despachoService.obtenerPorId(id);
        if (despacho == null)
            return ResponseEntity.notFound().build();
        despachoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
