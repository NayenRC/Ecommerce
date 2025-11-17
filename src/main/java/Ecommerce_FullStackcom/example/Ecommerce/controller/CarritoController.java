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

import Ecommerce_FullStackcom.example.Ecommerce.Service.CarritoService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Carrito;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    @Operation(summary = "Lista todos los carritos", description = "Obtiene todos los carritos registrados")
    public ResponseEntity<List<Carrito>> listar() {
        List<Carrito> carritos = carritoService.listar();
        return carritos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carritos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un carrito por ID", description = "Busca un carrito según su identificador")
    public ResponseEntity<Carrito> obtener(@PathVariable Integer id) {
        Carrito carrito = carritoService.obtenerPorId(id);
        return carrito != null ? ResponseEntity.ok(carrito) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo carrito", description = "Registra un carrito en la base de datos")
    public ResponseEntity<Carrito> crear(@Valid @RequestBody Carrito carrito) {
        Carrito creado = carritoService.guardar(carrito);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un carrito por ID", description = "Reemplaza completamente los datos de un carrito existente")
    public ResponseEntity<Carrito> actualizar(@PathVariable Integer id, @Valid @RequestBody Carrito carrito) {
        Carrito actualizado = carritoService.actualizarTodo(id, carrito);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualización parcial del carrito", description = "Permite modificar solo campos específicos del carrito")
    public ResponseEntity<Carrito> patch(@PathVariable Integer id, @RequestBody Carrito carrito) {
        Carrito actualizado = carritoService.patchCarrito(id, carrito);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un carrito", description = "Borra un carrito por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Carrito carrito = carritoService.obtenerPorId(id);
        if (carrito == null)
            return ResponseEntity.notFound().build();
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
