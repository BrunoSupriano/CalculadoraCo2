package nature.calculadoraco22;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import nature.calculadoraco22.Dto.EmissionImpactDto;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Repositories.EmissionRepository;
import nature.calculadoraco22.Service.EmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmisssionServiceTest {

    @Mock
    private EmissionRepository emissionRepository;

    @InjectMocks
    private EmissionService emissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateEmissionImpact() {
        Emission emission1 = new Emission();
        emission1.setCo2(30.0);
        emission1.setMonth(12);
        emission1.setYear(2024);

        Emission emission2 = new Emission();
        emission2.setCo2(17.0);
        emission2.setMonth(11);
        emission2.setYear(2023);

        List<Emission> emissions = Arrays.asList(emission1, emission2);

        when(emissionRepository.findByUserId(1L)).thenReturn(emissions);

        EmissionImpactDto impactDto = emissionService.calculateEmissionImpact(1L);

        assertEquals(47.0, impactDto.getTotalCO2());

        double averageAnnualCO2 = (30.0 + 17.0) / 2; // Média anual
        double tenYearPrevision = averageAnnualCO2 * 10;
        double co2InTonnes = tenYearPrevision / 1000;
        double treesRequired = co2InTonnes * 7;

        assertEquals(tenYearPrevision, impactDto.getTenYearPrevision(), 0.1);
        assertEquals(treesRequired, impactDto.getTreesRequired(), 0.001);
        assertTrue(impactDto.getTenYearPrevisionDescription().contains(String.format("%.2f toneladas", co2InTonnes)));
    }
}
