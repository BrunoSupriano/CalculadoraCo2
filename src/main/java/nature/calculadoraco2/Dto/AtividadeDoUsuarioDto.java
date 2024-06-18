package nature.calculadoraco2.Dto;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.Usuario;

public record AtividadeDoUsuarioDto (
        Long id,
        Usuario usuario,
        Atividade atividade,
        Double quantidade
){

}
