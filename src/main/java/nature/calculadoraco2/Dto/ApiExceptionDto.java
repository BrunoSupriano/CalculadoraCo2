package nature.calculadoraco2.Dto;

import java.time.Instant;

public record ApiExceptionDto(
        String titulo,
        String descricao,
        Instant data
) {

}
