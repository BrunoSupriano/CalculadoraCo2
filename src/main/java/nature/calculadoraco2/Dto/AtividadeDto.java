package nature.calculadoraco2.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AtividadeDto(

        Long id,

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @Positive(message = "A quantidade de CO2 deve ser positiva")
        Double quantidadeCo2,
        Long usuarioId

) {}
