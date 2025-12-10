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

import Ecommerce_FullStackcom.example.Ecommerce.Service.UsuarioService;
import Ecommerce_FullStackcom.example.Ecommerce.dto.LoginResponse;
import Ecommerce_FullStackcom.example.Ecommerce.model.Rol;
import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;
import Ecommerce_FullStackcom.example.Ecommerce.security.JwtUtil;
import Ecommerce_FullStackcom.example.Ecommerce.security.JwtUtil;
import Ecommerce_FullStackcom.example.Ecommerce.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private JwtUtil jwtUtil;
    private UsuarioService usuarioServicio;

    @GetMapping
    @Operation(summary = "Obtiene todos los usuarios", description = "Lista todos los usuarios registrados")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioServicio.listar();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene usuario por ID", description = "Busca un usuario según su identificador único")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo usuario", description = "Registra un nuevo usuario en la base de datos")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {

        // ASIGNAR ROL POR DEFECTO
        Rol rolUsuario = new Rol();
        rolUsuario.setRol_id(2);
        usuario.setRol(rolUsuario);

        Usuario creado = usuarioServicio.guardar(usuario);
        return ResponseEntity.status(201).body(creado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {

        Usuario login = usuarioServicio.login(usuario);

        if (login != null) {

            // Generar token JWT
            String token = jwtUtil.generateToken(login.getCorreoElectronico());

            // No devolver la contraseña
            login.setClave(null);

            // Crear respuesta con token + usuario
            LoginResponse response = new LoginResponse(token, login);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un usuario", description = "Actualiza completamente los datos de un usuario existente")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioServicio.actualizarTodo(id, usuario);
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza parcialmente un usuario", description = "Permite modificar parcialmente los datos de un usuario")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioServicio.patchUsuario(id, usuario);
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario existente por su ID")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
