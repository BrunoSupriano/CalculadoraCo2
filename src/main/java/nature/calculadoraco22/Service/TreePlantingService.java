package nature.calculadoraco22.Service;

import nature.calculadoraco22.Dto.TreePlantingDto;
import nature.calculadoraco22.Dto.TreePlantingTotalsDto;
import nature.calculadoraco22.Model.TreePlanting;
import nature.calculadoraco22.Repositories.TreePlantingRepository;
import nature.calculadoraco22.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreePlantingService {

    private final TreePlantingRepository treePlantingRepository;
    private final UserRepository userRepository;

    public TreePlantingService(TreePlantingRepository treePlantingRepository, UserRepository userRepository) {
        this.treePlantingRepository = treePlantingRepository;
        this.userRepository = userRepository;
    }

    public TreePlanting addTreePlanting(Long userId, TreePlantingDto treePlantingDto) {
        TreePlanting treePlanting = new TreePlanting();
        treePlanting.setNumberOfTrees(treePlantingDto.getNumberOfTrees());
        treePlanting.setPlantingDate(treePlantingDto.getPlantingDate());

        treePlanting.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId)));

        // Setar CO2 evitado com base na quantidade de árvores plantadas
        double co2Avoided = treePlantingDto.getNumberOfTrees() * 5.0; // Assumindo 5 kg de CO2 por árvore por ano
        treePlanting.setCO2Avoided(co2Avoided);

        treePlantingRepository.save(treePlanting);

        return treePlanting;
    }

    public List<TreePlanting> getAllTreePlantings(Long userId) {
        return treePlantingRepository.findByUserId(userId);
    }

    public TreePlanting getTreePlantingById(Long userId, Long id) {
        return treePlantingRepository.findByUserIdAndId(userId, id);
    }

    public void deleteTreePlanting(Long userId, Long id) {
        TreePlanting treePlanting = treePlantingRepository.findByUserIdAndId(userId, id);
        if (treePlanting != null) {
            treePlantingRepository.delete(treePlanting);
        }
    }

    @Transactional(readOnly = true)
    public TreePlantingTotalsDto getTreePlantingTotals(Long userId) {
        List<TreePlanting> plantings = treePlantingRepository.findByUserId(userId);

        double totalTreesPlanted = plantings.stream().mapToDouble(TreePlanting::getNumberOfTrees).sum();
        double totalCO2Avoided = plantings.stream().mapToDouble(TreePlanting::getCO2Avoided).sum();

        TreePlantingTotalsDto totalsDto = new TreePlantingTotalsDto();
        totalsDto.setTotalTreesPlanted(totalTreesPlanted);
        totalsDto.setTotalCO2Avoided(totalCO2Avoided);
        totalsDto.setExplanation(String.format("Com essa quantidade de árvores plantadas, está sendo absorvido aproximadamente %.2f toneladas de CO2 por ano.", totalCO2Avoided / 1000.0));

        return totalsDto;
    }
}
