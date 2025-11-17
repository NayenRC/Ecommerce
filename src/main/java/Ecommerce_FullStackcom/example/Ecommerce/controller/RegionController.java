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

import Ecommerce_FullStackcom.example.Ecommerce.Service.RegionService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Region;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionService regionServicio;

    @GetMapping
    @Operation(summary = "Obtiene todas las regiones", description = "Lista todas las regiones disponibles")
    public ResponseEntity<List<Region>> listar() {
        List<Region> regiones = regionServicio.listar();
        return regiones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(regiones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una región por ID", description = "Busca una región específica por su identificador")
    public ResponseEntity<Region> obtenerPorId(@PathVariable Integer id) {
        Region region = regionServicio.obtenerPorId(id);
        return (region != null) ? ResponseEntity.ok(region) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea una región", description = "Registra una nueva región en la base de datos")
    public ResponseEntity<Region> crear(@Valid @RequestBody Region region) {
        Region creada = regionServicio.guardar(region);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una región", description = "Actualiza completamente los datos de una región")
    public ResponseEntity<Region> actualizar(@PathVariable Integer id, @Valid @RequestBody Region region) {
        Region actualizada = regionServicio.actualizarTodo(id, region);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita parcialmente una región", description = "Permite modificar parcialmente los datos de una región")
    public ResponseEntity<Region> patch(@PathVariable Integer id, @RequestBody Region region) {
        Region actualizada = regionServicio.patchRegion(id, region);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una región", description = "Elimina una región existente por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Region region = regionServicio.obtenerPorId(id);
        if (region == null) return ResponseEntity.notFound().build();
        regionServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
