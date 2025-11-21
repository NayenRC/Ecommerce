package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;
import Ecommerce_FullStackcom.example.Ecommerce.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardar(Usuario usuario) {
        // Encriptar la contraseña ANTES de guardar
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarTodo(Integer id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setCorreoElectronico(usuario.getCorreoElectronico());
        existente.setNombreUsuario(usuario.getNombreUsuario());
        existente.setClave(usuario.getClave());
        existente.setDireccion(usuario.getDireccion());
        existente.setRol(usuario.getRol());
        existente.setComuna(usuario.getComuna());

        return usuarioRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    public Usuario patchUsuario(Integer id, Usuario usuarioParcial) {
        Usuario existente = obtenerPorId(id);
        if (existente != null) {
            if (usuarioParcial.getCorreoElectronico() != null)
                existente.setCorreoElectronico(usuarioParcial.getCorreoElectronico());
            if (usuarioParcial.getNombreUsuario() != null)
                existente.setNombreUsuario(usuarioParcial.getNombreUsuario());
            if (usuarioParcial.getClave() != null)
                existente.setClave(usuarioParcial.getClave());
            if (usuarioParcial.getDireccion() != null)
                existente.setDireccion(usuarioParcial.getDireccion());
            if (usuarioParcial.getRol() != null)
                existente.setRol(usuarioParcial.getRol());
            if (usuarioParcial.getComuna() != null)
                existente.setComuna(usuarioParcial.getComuna());

            return guardar(existente);
        }
        return null;
    }

    public Usuario login(Usuario usuario) {
        Usuario encontrado = usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());

        if (encontrado != null && passwordEncoder.matches(usuario.getClave(), encontrado.getClave())) {
            return encontrado;
        }
        return null;
    }

}
