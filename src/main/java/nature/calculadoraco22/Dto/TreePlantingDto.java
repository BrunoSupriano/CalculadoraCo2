package nature.calculadoraco22.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TreePlantingDto {

    private Integer numberOfTrees;
    private LocalDate plantingDate;
    private Long userId;

}
