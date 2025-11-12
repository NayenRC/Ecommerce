package Ecommerce_FullStackcom.example.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecommerce_FullStackcom.example.Ecommerce.Service.RolService;
import Ecommerce_FullStackcom.example.Ecommerce.model.Rol;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    @Autowired
    private RolService rolServicio;

    @GetMapping
    @Operation(summary = "Lista todos los roles", description = "Obtiene todos los roles disponibles")
    public ResponseEntity<List<Rol>> listar() {
        List<Rol> roles = rolServicio.listar();
        return roles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un rol por ID", description = "Busca un rol específico por su ID")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Integer id) {
        Rol rol = rolServicio.obtenerPorId(id);
        return (rol != null) ? ResponseEntity.ok(rol) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un rol", description = "Registra un nuevo rol en el sistema")
    public ResponseEntity<Rol> crear(@Valid @RequestBody Rol rol) {
        Rol creado = rolServicio.guardar(rol);
        return ResponseEntity.status(201).body(creado);
    }


}
