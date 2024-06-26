package nature.calculadoraco22.Mappers;

import nature.calculadoraco22.Dto.TreePlantingDto;
import nature.calculadoraco22.Model.TreePlanting;
import org.springframework.stereotype.Component;

@Component
public class TreePlantingMapper {
    public TreePlantingDto toDto(TreePlanting treePlanting) {
        return new TreePlantingDto(
                treePlanting.getNumberOfTrees(),
                treePlanting.getPlantingDate(),
                treePlanting.getUser().getId()
        );
    }
    public TreePlanting toEntity(TreePlantingDto treePlantingDto) {
        TreePlanting treePlanting = new TreePlanting();
        treePlanting.setNumberOfTrees(treePlantingDto.getNumberOfTrees());
        treePlanting.setPlantingDate(treePlantingDto.getPlantingDate());
        return treePlanting;
    }
}
