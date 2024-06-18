package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.AtividadeDoUsuarioDto;
import nature.calculadoraco2.Model.AtividadedoUsuario;
import org.springframework.stereotype.Component;

@Component
public class AtividadeDoUsuarioMapper {
    public AtividadeDoUsuarioDto toDto(AtividadedoUsuario atividadedoUsuario) {
        return new AtividadeDoUsuarioDto(
                atividadedoUsuario.getId(),
                atividadedoUsuario.getUsuario(),
                atividadedoUsuario.getAtividade(),
                atividadedoUsuario.getQuantidade()
        );
    }
}
