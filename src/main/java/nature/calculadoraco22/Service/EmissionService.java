package nature.calculadoraco22.Service;

import nature.calculadoraco22.Dto.EmissionDto;
import nature.calculadoraco22.Dto.EmissionImpactDto;
import nature.calculadoraco22.Dto.EmissionSummaryByYearDto;
import nature.calculadoraco22.Mappers.EmissionMapper;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Repositories.EmissionRepository;
import nature.calculadoraco22.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmissionService {

    @Autowired
    private final EmissionRepository emissionRepository;

    @Autowired
    EmissionMapper emissionMapper;

    private final UserRepository userRepository;

    public EmissionService(EmissionRepository emissionRepository, UserRepository userRepository) {
        this.emissionRepository = emissionRepository;
        this.userRepository = userRepository;
    }

    public EmissionDto addEmission(Long userId, EmissionDto emissionDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User user = optionalUser.get();
        Emission emission = emissionMapper.toEntity(emissionDto, user);

        Emission savedEmission = emissionRepository.save(emission);
        return emissionMapper.toDto(savedEmission);
    }

    public List<EmissionDto> getEmissionsByUser(Long userId, Integer pagina, Integer resultados, List<String> sortBy) {
        List<Sort.Order> orderByList = sortBy.stream()
                .map((field) -> new Sort.Order(Sort.Direction.ASC, field))
                .collect(Collectors.toList());
        Pageable pageConfig = PageRequest.of(pagina, resultados, Sort.by(orderByList));
        Page<Emission> listaEmissao = emissionRepository.findAll(pageConfig);
        List<EmissionDto> listaEmissaoDto = listaEmissao.stream().map(emissionMapper::toDto).toList();
        return listaEmissaoDto;
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

    // Calculos de sumário de emissões
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

        double averageAnnualPrevision = annualTotals.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double tenYearPrevision = averageAnnualPrevision * 10;
        double co2InTonnes = tenYearPrevision / 1000;

        // Assuming 7 trees are required to absorb 1 tonne of CO2
        double treesRequired = co2InTonnes * 7;

        String tenYearPrevisionDescription = String.format("Em 10 anos, a quantidade de CO2 emitida será de %.2f toneladas. Para compensar essa emissão, será necessário plantar aproximadamente %.2f árvores.", co2InTonnes, treesRequired);
        String informativo = "Para cada tonelada de CO2 emitida, são necessárias cerca de 7 árvores para absorver essa quantidade de CO2 ao longo de um ano. Por exemplo, um carro médio emite cerca de 0,12 kg de CO2 por quilômetro rodado. Portanto, para cada 8.333 km dirigidos, uma árvore deve ser plantada para compensar as emissões de gases de efeito estufa.";

        return new EmissionImpactDto(totalCO2, tenYearPrevision, treesRequired, tenYearPrevisionDescription, informativo);
    }

    private String getMonthName(int month) {
        return switch (month) {
            case 1 -> "janeiro";
            case 2 -> "fevereiro";
            case 3 -> "março";
            case 4 -> "abril";
            case 5 -> "maio";
            case 6 -> "junho";
            case 7 -> "julho";
            case 8 -> "agosto";
            case 9 -> "setembro";
            case 10 -> "outubro";
            case 11 -> "novembro";
            case 12 -> "dezembro";
            default -> "";
        };
    }

    public void deleteEmission(Long userId, Long id) {
        Emission emission = emissionRepository.findByUserIdAndId(userId, id);
        if (emission != null) {
            emissionRepository.delete(emission);
        } else {
            throw new RuntimeException("Emissão não encontrada");
        }
    }
}
