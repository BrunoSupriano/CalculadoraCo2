package nature.calculadoraco2.Dto;

import java.time.Month;
import java.time.Year;

public record EmissaoDto (
        Long id,
        Month mes,
        Year ano,
        double totalco2,
        Long usuarioId
) { }
