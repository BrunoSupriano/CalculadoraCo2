package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.EmissaoDto;
import nature.calculadoraco2.Model.Emissao;
import org.springframework.stereotype.Component;

@Component
public class EmissaoMapper {

    public Emissao toEntity(EmissaoDto dto) {
        Emissao emissao = new Emissao();
        emissao.setId(dto.id());
        emissao.setMes(dto.mes());
        emissao.setAno(dto.ano());
        emissao.setTotalCo2(dto.totalco2());
        return emissao;
    }

    public EmissaoDto toDto(Emissao emissao) {
        return new EmissaoDto(
                emissao.getId(),
                emissao.getMes(),
                emissao.getAno(),
                emissao.getTotalCo2(),
                emissao.getUsuario().getId()
        );
    }
}