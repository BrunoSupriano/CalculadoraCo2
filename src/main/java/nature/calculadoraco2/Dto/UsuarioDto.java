package nature.calculadoraco2.Dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @Email(message = "Email deve ser válido")
        @NotBlank(message = "O email não pode estar em branco")
        String email
) {}