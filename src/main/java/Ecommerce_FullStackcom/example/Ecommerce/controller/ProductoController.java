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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import Ecommerce_FullStackcom.example.Ecommerce.Service.CategoriaService;
import Ecommerce_FullStackcom.example.Ecommerce.Service.ProductoService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Categoria;
import Ecommerce_FullStackcom.example.Ecommerce.model.Producto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Lista todos los productos", description = "Obtiene todos los productos disponibles")
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.listar();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un producto por su id", description = "Busca un producto específico por su identificador")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        Producto producto = productoService.obtenerPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un producto", description = "Registra un nuevo producto")
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        Producto creado = productoService.guardar(producto);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto", description = "Actualiza los datos de un producto")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @Valid @RequestBody Producto producto) {
        Producto actualizado = productoService.actualizarTodo(id, producto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita parcialmente un producto", description = "Modifica parcialmente los datos de un producto")
    public ResponseEntity<Producto> patch(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto actualizado = productoService.patchProducto(id, producto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto", description = "Elimina un producto por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) return ResponseEntity.notFound().build();
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre")
    @Operation(summary = "Busca productos por nombre", description = "Filtra los productos por su nombre")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/categoria")
    @Operation(summary = "Filtra productos por categoría", description = "Filtra todos los productos que pertenecen a una categoría")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@RequestParam Integer categoriaId) {
    Categoria categoria = categoriaService.obtenerPorId(categoriaId);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        List<Producto> productos = productoService.buscarPorCategoria(categoria);
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }  
}
