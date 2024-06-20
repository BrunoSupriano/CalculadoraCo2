package nature.calculadoraco2.Dto;
import nature.calculadoraco2.Model.Atividade;

public record EmissaoDto (
        Long id,
        Atividade atividadedoUsuario,
        Double totalEmissao
) {
}
