package nature.calculadoraco2.Mapper;

import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Model.Atividade;
import org.springframework.stereotype.Component;

@Component
public class AtividadeMapper {

    public Atividade toEntity(AtividadeDto dto) {
        Atividade atividade = new Atividade();
        atividade.setId(dto.id());
        atividade.setNome(dto.nome());
        atividade.setQuantidadeCo2(dto.quantidadeCo2());
        atividade.setUsuarioId(dto.usuarioId());
        return atividade;
    }

    public AtividadeDto toDto(Atividade atividade) {
        return new AtividadeDto(
                atividade.getId(),
                atividade.getNome(),
                atividade.getQuantidadeCo2(),
                atividade.getUsuario().getId()
        );
    }
}