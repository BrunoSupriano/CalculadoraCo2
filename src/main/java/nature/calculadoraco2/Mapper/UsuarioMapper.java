package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.UsuarioDto;
import nature.calculadoraco2.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail()
        );
    }
}
