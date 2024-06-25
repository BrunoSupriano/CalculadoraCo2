package nature.calculadoraco22;

import nature.calculadoraco22.Dto.TreePlantingDto;
import nature.calculadoraco22.Dto.TreePlantingTotalsDto;
import nature.calculadoraco22.Model.TreePlanting;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Repositories.TreePlantingRepository;
import nature.calculadoraco22.Repositories.UserRepository;
import nature.calculadoraco22.Service.TreePlantingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TreePlantingServiceTest {

    @Mock
    private TreePlantingRepository treePlantingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TreePlantingService treePlantingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTreePlanting() {
        // Mockando o retorno do userRepository
        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(mockUser));

        // Criando TreePlantingDto
        TreePlantingDto treePlantingDto = new TreePlantingDto();
        treePlantingDto.setNumberOfTrees(10);
        treePlantingDto.setPlantingDate(LocalDate.now());

        // Mockando o retorno do treePlantingRepository.save()
        when(treePlantingRepository.save(any(TreePlanting.class))).thenAnswer(invocation -> {
            TreePlanting treePlanting = invocation.getArgument(0);
            treePlanting.setId(1L); // Atribuindo um ID fixo (exemplo: 1L) para simular a persistência
            treePlanting.setCO2Avoided(treePlantingDto.getNumberOfTrees() * 5.0); // Calculando o CO2 evitado
            return treePlanting;
        });

        // Testando o método addTreePlanting
        TreePlanting addedTreePlanting = treePlantingService.addTreePlanting(1L, treePlantingDto);

        // Verificações
        assertEquals(1L, addedTreePlanting.getId());
        assertEquals(10, addedTreePlanting.getNumberOfTrees());
        assertEquals(treePlantingDto.getPlantingDate(), addedTreePlanting.getPlantingDate());
        assertEquals(50.0, addedTreePlanting.getCO2Avoided()); // Verifica o CO2 evitado com base na quantidade de árvores
    }
}