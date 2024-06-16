package nature.calculadoraco2.Service;

import nature.calculadoraco2.Model.Usuario;
import nature.calculadoraco2.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(long l) {
        return usuarioRepository.findById(l).orElse(null);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Other service methods
}