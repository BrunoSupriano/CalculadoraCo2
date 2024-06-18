package nature.calculadoraco2.Dto;

import nature.calculadoraco2.Model.AtividadedoUsuario;

public record EmissaoDto (
        Long id,
        AtividadedoUsuario atividadedoUsuario,
        Double totalEmissao
) {
}
