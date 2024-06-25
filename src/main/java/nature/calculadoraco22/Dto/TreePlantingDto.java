package nature.calculadoraco22.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TreePlantingDto {

    private int numberOfTrees;
    private LocalDate plantingDate;
    private Long userId;

}
