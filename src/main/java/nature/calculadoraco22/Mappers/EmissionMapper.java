package nature.calculadoraco22.Mappers;

import nature.calculadoraco22.Dto.EmissionDto;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Model.User;
import org.springframework.stereotype.Component;



@Component
public class EmissionMapper {
    public EmissionDto toDto(Emission emission) {
        return new EmissionDto(
                emission.getActivity(),
                emission.getCo2(),
                emission.getMonth(),
                emission.getYear()
        );
    }
    public Emission toEntity (EmissionDto emissionDto, User user) {
        Emission emission = new Emission();
        emission.setActivity(emissionDto.getActivity());
        emission.setCo2(emissionDto.getCo2());
        emission.setMonth(emissionDto.getMonth());
        emission.setYear(emissionDto.getYear());
        emission.setUser(user);
        return emission;

    }
}
