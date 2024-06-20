package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.EmissaoDto;
import nature.calculadoraco2.Model.CalculoEmissao;
import org.springframework.stereotype.Component;

@Component
public class EmissaoMapper {
    public EmissaoDto toDto(CalculoEmissao emissao) {
        return new EmissaoDto(
                emissao.getId(),
                emissao.getUsuario().getId(),
                emissao.getTotalEmissao()
        );
    }
}