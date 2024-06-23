package nature.calculadoraco22.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class EmissionSummaryDto {
    private int year;
    private Map<String, Double> monthlyTotals;
    private double totalAnnualCO2;
    private double totalAnnualCO2Prevision;

}