package nature.calculadoraco22.Service;

import nature.calculadoraco22.Dto.EmissionDto;
import nature.calculadoraco22.Model.Emission;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Repositories.EmissionRepository;
import nature.calculadoraco22.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionService {

    @Autowired
    private EmissionRepository emissionRepository;

    @Autowired
    private UserRepository userRepository;

    public Emission addEmission(Long userId, EmissionDto emissionDto) {
        Emission emission = new Emission();
        emission.setActivity(emissionDto.getActivity());
        emission.setCo2(emissionDto.getCo2());
        emission.setMonth(emissionDto.getMonth());
        emission.setYear(emissionDto.getYear());

        // Aqui você precisa configurar o usuário (User) relacionado à emissão
        // Para simplificar, vou assumir que você já tem o usuário com o ID `userId`
        User user = new User();
        user.setId(userId);
        emission.setUser(user);

        return emissionRepository.save(emission);
    }

    public List<Emission> getEmissionsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEmissions();
    }

    public double getTotalEmissionsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEmissions().stream().mapToDouble(Emission::getCo2).sum();
    }
    /// verificar
    public List<Emission> getEmissionsByUserIdAndMonthAndYear(Long userId, int month, int year) {
        return emissionRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    public List<Emission> getEmissionsByUserIdAndYear(Long userId, int year) {
        return emissionRepository.findByUserIdAndYear(userId, year);
    }

    public double getTotalCO2ByUserIdAndYear(Long userId, int year) {
        List<Emission> emissions = emissionRepository.findByUserIdAndYear(userId, year);
        double totalCO2 = emissions.stream().mapToDouble(Emission::getCo2).sum();
        return totalCO2;
    }
}