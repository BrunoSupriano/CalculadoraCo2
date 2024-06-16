package nature.calculadoraco2.Service;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.AtividadedoUsuario;
import nature.calculadoraco2.Model.Usuario;
import nature.calculadoraco2.Model.Emissao;
import nature.calculadoraco2.Repositories.AtividadeRepository;
import nature.calculadoraco2.Repositories.UsuarioRepository;
import nature.calculadoraco2.Repositories.EmissaoRepository;
import nature.calculadoraco2.Repositories.AtividadedoUsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AtividadedoUsuarioRepository userActivity;

    @Autowired
    private EmissaoRepository emissaoRepository;

    public List<Atividade> getAllActivities() {
        return atividadeRepository.findAll();
    }

    public Atividade getActivityById(Long id) {
        return atividadeRepository.findById(id.intValue()).orElse(null);
    }

    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public AtividadedoUsuario createUserActivity(AtividadedoUsuario userActivity) {
        return this.userActivity.save(userActivity);
    }

    public Emissao calculateEmissions(AtividadedoUsuario userActivity) {
        double totalEmission = userActivity.getCorrectActivityMethod().getEmissionFactor() * userActivity.getCorrectQuantityMethod();
        Emissao emission = new Emissao(userActivity, totalEmission);
        return emissaoRepository.save(emission);
    }

    public List<nature.calculadoraco2.Model.Emissao> getEmissionsByUser(Long userId) {
        Usuario user = getUserById(userId);
        return emissaoRepository.findByAtividadedoUsuarioUsuario(user);
    }

    public void deleteActivity(Long id) {
        atividadeRepository.deleteById(id.intValue());
    }
}