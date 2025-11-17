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
import Ecommerce_FullStackcom.example.Ecommerce.Service.ProductoCarritoService;
import Ecommerce_FullStackcom.example.Ecommerce.model.ProductoCarrito;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productosCarrito")
public class ProductoCarritoController {

    @Autowired
    private ProductoCarritoService productoCarritoService;

    @GetMapping
    @Operation(summary = "Lista todos los productos en carrito", description = "Obtiene todos los productos agregados a carritos")
    public ResponseEntity<List<ProductoCarrito>> listar() {
        List<ProductoCarrito> productos = productoCarritoService.listar();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un producto en carrito por ID", description = "Busca un producto en el carrito por su ID")
    public ResponseEntity<ProductoCarrito> obtenerPorId(@PathVariable Integer id) {
        ProductoCarrito producto = productoCarritoService.obtenerPorId(id);
        return (producto != null) ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Agrega un producto al carrito", description = "Registra un nuevo producto en el carrito")
    public ResponseEntity<ProductoCarrito> crear(@Valid @RequestBody ProductoCarrito productoCarrito) {
        ProductoCarrito creado = productoCarritoService.guardar(productoCarrito);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto en carrito", description = "Actualiza los datos de un producto en carrito")
    public ResponseEntity<ProductoCarrito> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoCarrito productoCarrito) {
        ProductoCarrito actualizado = productoCarritoService.actualizarTodo(id, productoCarrito);
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita parcialmente un producto en carrito", description = "Modifica parcialmente los datos de un producto en carrito")
    public ResponseEntity<ProductoCarrito> patch(@PathVariable Integer id, @RequestBody ProductoCarrito productoCarrito) {
        ProductoCarrito actualizado = productoCarritoService.patchProductoCarrito(id, productoCarrito);
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto del carrito", description = "Elimina un producto por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ProductoCarrito producto = productoCarritoService.obtenerPorId(id);
        if (producto == null)
            return ResponseEntity.notFound().build();
        productoCarritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}