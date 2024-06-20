package nature.calculadoraco2.Service;
import nature.calculadoraco2.Dto.UsuarioDto;
import nature.calculadoraco2.Mapper.UsuarioMapper;
import nature.calculadoraco2.Model.Usuario;
import nature.calculadoraco2.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDto> getAllUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios.stream().map(usuarioMapper::toDto).toList();
    }

    public UsuarioDto getUsuarioById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) throw new RuntimeException("O usuário não existe");
        return usuarioMapper.toDto(usuario.get());
    }

    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setName(usuarioDto.name());
        usuario.setEmail(usuarioDto.email());
        usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto updateUsuario(Long id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setName(usuarioDto.name());
        usuario.setEmail(usuarioDto.email());
        usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
        return usuarioMapper.toDto(usuario);
    }
}