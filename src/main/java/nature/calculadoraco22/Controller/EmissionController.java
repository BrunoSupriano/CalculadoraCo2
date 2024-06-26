package nature.calculadoraco22.Controller;

import nature.calculadoraco22.Dto.EmissionDto;
import nature.calculadoraco22.Dto.EmissionImpactDto;
import nature.calculadoraco22.Dto.EmissionSummaryByYearDto;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Service.EmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/{userId}/emissions")
public class EmissionController {

    private final EmissionService emissionService;

    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    // Post/Get controlado pelo usuario

    @PostMapping
    public EmissionDto addEmission(@PathVariable Long userId, @RequestBody EmissionDto emissionDto) {
        return emissionService.addEmission(userId, emissionDto);
    }

    @GetMapping
    public List<EmissionDto> getEmissionsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "5") Integer resultados,
            @RequestParam(defaultValue = "month") List<String> sortBy) {

        return emissionService.getEmissionsByUser(userId, pagina, resultados, sortBy);
    }

    // Gets para agrupar por ano e mes as emissoes

    @GetMapping("/{year}")
    public List<Emission> getEmissionsByUserIdAndYear(@PathVariable Long userId, @PathVariable int year) {
        return emissionService.getEmissionsByUserIdAndYear(userId, year);
    }

    @GetMapping("/{year}/{month}")
    public List<Emission> getEmissionsByUserIdAndMonthAndYear(@PathVariable Long userId, @PathVariable int year, @PathVariable int month) {
        return emissionService.getEmissionsByUserIdAndMonthAndYear(userId, year, month);
    }

    // Sumário para mostrar o total de emissões por ano e mes organizado
    @GetMapping("/summary")
    public List<EmissionSummaryByYearDto> getTotalEmissionsByUser(@PathVariable Long userId) {
        return emissionService.getTotalEmissionsByUser(userId);
    }

    // Total de CO2 por ano
    @GetMapping("/total/{year}")
    public Map<String, Double> getTotalCO2ByUserIdAndYear(@PathVariable Long userId, @PathVariable int year) {
        double totalCO2 = emissionService.getTotalCO2ByUserIdAndYear(userId, year);

        Map<String, Double> result = new HashMap<>();
        result.put("totalCO2", totalCO2);

        return result;
    }

    // Calcular impacto geral das emissoes do ussuario
    @GetMapping("/impact")
    public EmissionImpactDto getEmissionImpact(@PathVariable Long userId) {
        return emissionService.calculateEmissionImpact(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmission(
            @PathVariable Long userId,
            @PathVariable Long id) {
        emissionService.deleteEmission(userId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
