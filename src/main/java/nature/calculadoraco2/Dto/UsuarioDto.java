package nature.calculadoraco2.Dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
        Long id,
        @NotBlank(message = "O nome não pode estar em branco")
        String name,
        @NotBlank(message = "O e-mail não pode estar em branco")
        String email
) {
}
