package nature.calculadoraco22.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter

public class EmissionSummaryByYearDto {
    private int year;
    private Map<String, Double> monthlyTotals = new LinkedHashMap<>();
    private double totalAnnualCO2;
    private double totalAnnualCO2Prevision;
}
