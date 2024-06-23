package nature.calculadoraco22.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmissionDto {
    private String activity;
    private double co2;
    private int month;
    private int year;
}
