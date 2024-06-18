package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.EmissaoDto;
import nature.calculadoraco2.Model.Emissao;
import org.springframework.stereotype.Component;

@Component
public class EmissaoMapper {
    public EmissaoDto toDto(Emissao emissao) {
        return new EmissaoDto(
                emissao.getId(),
                emissao.getAtividadedoUsuario(),
                emissao.getTotalEmissao()
        );
    }
}