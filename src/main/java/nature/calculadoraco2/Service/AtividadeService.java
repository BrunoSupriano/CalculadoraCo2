package nature.calculadoraco2.Service;
import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.Emissao;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

    private AtividadeRepository activityRepository;
    private UsarioRepository userRepository;
    private AtividadeUsuarioRepository userActivityRepository;
    private EmissaoRepository emissionRepository;

    public List<Atividade> getAllActivities() {
        return activityRepository.findAll();
    }

    public Atividade getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    public static User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserActivity createUserActivity(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);
    }

    public Emissao calculateEmissions(UserActivity userActivity) {
        double totalEmission = userActivity.getActivity().getEmissionFactor() * userActivity.getQuantity();
        Emission emission = new Emission();
        emission.setUserActivity(userActivity);
        emission.setTotalEmission(totalEmission);
        return emissionRepository.save(emission);
    }

    public List<Emission> getEmissionsByUser(User user) {
        return emissionRepository.findByUserActivityUser(user);
    }
}