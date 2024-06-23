package nature.calculadoraco22.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {
    private String username;
    private List<EmissionDto> emissions;

}