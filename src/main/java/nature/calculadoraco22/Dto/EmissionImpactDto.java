package nature.calculadoraco22.Dto;

import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Getter
@Setter

@AllArgsConstructor
public class EmissionImpactDto {
    private double totalCO2;
    private double tenYearPrevision;
    private double treesRequired;
    private String tenYearPrevisionDescription;
    private String informativo;
}