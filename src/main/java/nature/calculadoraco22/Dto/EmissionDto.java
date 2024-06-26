package nature.calculadoraco22.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmissionDto {
    private String activity;
    private Double co2;
    private Integer month;
    private Integer year;
}
