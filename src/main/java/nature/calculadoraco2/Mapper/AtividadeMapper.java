package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Model.Atividade;
import org.springframework.stereotype.Component;

@Component
public class AtividadeMapper {
    public AtividadeDto toDto(Atividade atividade) {
        return new AtividadeDto(
                atividade.getId(),
                atividade.getName(),
                atividade.getEmissionFactor()
        );
    }
}
