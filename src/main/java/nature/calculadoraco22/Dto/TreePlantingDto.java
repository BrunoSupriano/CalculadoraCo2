package nature.calculadoraco22.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class TreePlantingDto {

    private int numberOfTrees;
    private LocalDate plantingDate;
    private Long userId;

}
