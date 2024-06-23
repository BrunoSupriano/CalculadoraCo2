package nature.calculadoraco22.Service;

import nature.calculadoraco22.Dto.EmissionDto;
import nature.calculadoraco22.Dto.EmissionImpactDto;
import nature.calculadoraco22.Dto.EmissionSummaryByYearDto;
import nature.calculadoraco22.Dto.EmissionSummaryDto;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Repositories.EmissionRepository;
import nature.calculadoraco22.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmissionService {

    private final EmissionRepository emissionRepository;

    private final UserRepository userRepository;

    public EmissionService(EmissionRepository emissionRepository, UserRepository userRepository) {
        this.emissionRepository = emissionRepository;
        this.userRepository = userRepository;
    }

    public Emission addEmission(Long userId, EmissionDto emissionDto) {
        Emission emission = new Emission();
        emission.setActivity(emissionDto.getActivity());
        emission.setCo2(emissionDto.getCo2());
        emission.setMonth(emissionDto.getMonth());
        emission.setYear(emissionDto.getYear());

        User user = new User();
        user.setId(userId);
        emission.setUser(user);

        return emissionRepository.save(emission);
    }

    public List<Emission> getEmissionsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEmissions();
    }

    public List<Emission> getEmissionsByUserIdAndYear(Long userId, int year) {
        return emissionRepository.findByUserIdAndYear(userId, year);
    }

    public List<Emission> getEmissionsByUserIdAndMonthAndYear(Long userId, int year, int month) {
        return emissionRepository.findByUserIdAndYearAndMonth(userId, year, month);
    }

    public double getTotalCO2ByUserIdAndYear(Long userId, int year) {
        List<Emission> emissions = emissionRepository.findByUserIdAndYear(userId, year);
        return emissions.stream().mapToDouble(Emission::getCo2).sum();
    }

    //Calculos de sumário de emissões

    public List<EmissionSummaryByYearDto> getTotalEmissionsByUser(Long userId) {
        List<Emission> emissions = emissionRepository.findByUserId(userId);

        // Agrupar emissões por ano
        Map<Integer, List<Emission>> emissionsByYear = emissions.stream()
                .collect(Collectors.groupingBy(Emission::getYear));

        // Calcular totais por ano e previsão anual
        List<EmissionSummaryByYearDto> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Emission>> entry : emissionsByYear.entrySet()) {
            int year = entry.getKey();
            List<Emission> emissionsOfYear = entry.getValue();

            // Calcular totais mensais para o ano
            Map<String, Double> monthlyTotals = new LinkedHashMap<>();
            double totalAnnualCO2 = 0.0;

            for (int month = 1; month <= 12; month++) {
                final int currentMonth = month;
                double totalCO2ForMonth = emissionsOfYear.stream()
                        .filter(e -> e.getMonth() == currentMonth)
                        .mapToDouble(Emission::getCo2)
                        .sum();

                monthlyTotals.put(getMonthName(currentMonth), totalCO2ForMonth);
                totalAnnualCO2 += totalCO2ForMonth;
            }

            // Calcular previsão anual
            double totalMonthsFilled = monthlyTotals.values().stream().filter(value -> value > 0).count();
            double averageCO2PerMonth = totalMonthsFilled > 0 ? totalAnnualCO2 / totalMonthsFilled : 0;
            double totalAnnualCO2Prevision = averageCO2PerMonth * 12;

            // Criar objeto DTO com os resultados
            EmissionSummaryByYearDto summaryDto = new EmissionSummaryByYearDto();
            summaryDto.setYear(year);
            summaryDto.setMonthlyTotals(monthlyTotals);
            summaryDto.setTotalAnnualCO2(totalAnnualCO2);
            summaryDto.setTotalAnnualCO2Prevision(totalAnnualCO2Prevision);

            result.add(summaryDto);
        }

        return result;
    }

    public EmissionImpactDto calculateEmissionImpact(Long userId) {
        List<Emission> emissions = emissionRepository.findByUserId(userId);
        double totalCO2 = emissions.stream().mapToDouble(Emission::getCo2).sum();

        Map<Integer, Double> annualTotals = emissions.stream()
                .collect(Collectors.groupingBy(Emission::getYear, Collectors.summingDouble(Emission::getCo2)));

        double averageAnnualPrevision = annualTotals.values().stream().mapToDouble(total -> total * 12).average().orElse(0.0);
        double tenYearPrevision = averageAnnualPrevision * 10;
        double co2InTonnes = tenYearPrevision / 1000;

        // Assuming 7 trees are required to absorb 1 tonne of CO2
        double treesRequired = co2InTonnes * 7;

        String tenYearPrevisionDescription = String.format("Em 10 anos, a quantidade de CO2 emitida será de %.2f toneladas. Para compensar essa emissão, será necessário plantar aproximadamente %.2f árvores.", co2InTonnes, treesRequired);
        String informativo = "Para cada tonelada de CO2 emitida, são necessárias cerca de 7 árvores para absorver essa quantidade de CO2 ao longo de um ano. Por exemplo, um carro médio emite cerca de 0,12 kg de CO2 por quilômetro rodado. Portanto, para cada 8.333 km dirigidos, uma árvore deve ser plantada para compensar as emissões de gases de efeito estufa.";

        return new EmissionImpactDto(totalCO2, tenYearPrevision, treesRequired, tenYearPrevisionDescription, informativo);
    }

    private String getMonthName(int month) {
        switch (month) {
            case 1: return "janeiro";
            case 2: return "fevereiro";
            case 3: return "março";
            case 4: return "abril";
            case 5: return "maio";
            case 6: return "junho";
            case 7: return "julho";
            case 8: return "agosto";
            case 9: return "setembro";
            case 10: return "outubro";
            case 11: return "novembro";
            case 12: return "dezembro";
            default: return "";
        }
    }


}