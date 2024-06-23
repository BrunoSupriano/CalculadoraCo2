package nature.calculadoraco22.Controller;

import nature.calculadoraco22.Dto.EmissionDto;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Service.EmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/{userId}/emissions") // Rota base para todas as operações relacionadas a emissões de um usuário
public class EmissionController {

    private final EmissionService emissionService;

    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @PostMapping
    public Emission addEmission(@PathVariable Long userId, @RequestBody EmissionDto emissionDto) {
        return emissionService.addEmission(userId, emissionDto);
    }

    @GetMapping
    public List<Emission> getEmissionsByUser(@PathVariable Long userId) {
        return emissionService.getEmissionsByUser(userId);
    }

    @GetMapping("/total")
    public double getTotalEmissionsByUser(@PathVariable Long userId) {
        return emissionService.getTotalEmissionsByUser(userId);
    }

    @GetMapping("/{year}/{month}") // Corrigido para não repetir userId
    public List<Emission> getEmissionsByUserIdAndMonthAndYear(@PathVariable Long userId, @PathVariable int year, @PathVariable int month) {
        return emissionService.getEmissionsByUserIdAndMonthAndYear(userId, year, month);
    }

    @GetMapping("/total/{year}") // Corrigido para não repetir userId
    public Map<String, Double> getTotalCO2ByUserIdAndYear(@PathVariable Long userId, @PathVariable int year) {
        double totalCO2 = emissionService.getTotalCO2ByUserIdAndYear(userId, year);

        Map<String, Double> result = new HashMap<>();
        result.put("totalCO2", totalCO2);

        return result;
    }

}
