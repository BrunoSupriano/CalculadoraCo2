package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.AtividadedoUsuario;
import nature.calculadoraco2.Model.Emissao;
import nature.calculadoraco2.Model.Usuario;
import nature.calculadoraco2.Service.AtividadeService;
import nature.calculadoraco2.Service.EmissaoService;
import nature.calculadoraco2.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmissaoService emissaoService;

    @GetMapping("/atividades")
    public List<Atividade> getAllActivities() {
        return atividadeService.getAllActivities();
    }

    @GetMapping("/atividades/{id}")
    public Atividade getActivityById(@PathVariable Long id) {
        return atividadeService.getActivityById(id);
    }

    @PostMapping("/atividades/criarAtividade")
    public ResponseEntity<AtividadedoUsuario> criarAtividade(@RequestBody AtividadedoUsuario userActivity) {
        AtividadedoUsuario createdActivity = atividadeService.createUserActivity(userActivity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @PostMapping("/emissoes/calcularEmissao")
    public ResponseEntity<Emissao> calcularEmissao(@RequestBody Emissao emission) {
        Emissao calculatedEmission = emissaoService.calculateEmission(emission);
        return new ResponseEntity<>(calculatedEmission, HttpStatus.CREATED);
    }

    @GetMapping("/emissoes/usuario/{userId}")
    public List<Emissao> listarEmissaoPorUsuario(@PathVariable Long userId) {
        return emissaoService.getEmissionsByUser(userId);
    }

    @PutMapping("/emissoes/{id}")
    public ResponseEntity<Emissao> atualizarEmissao(@PathVariable Long id, @RequestBody Emissao emission) {
        Emissao updatedEmission = emissaoService.updateEmission(id, emission);
        return ResponseEntity.ok(updatedEmission);
    }

    @DeleteMapping("/emissoes/{id}")
    public ResponseEntity<Void> deletarEmissao(@PathVariable Long id) {
        emissaoService.deleteEmission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUserById(@PathVariable Long id) {
        return usuarioService.getUserById(id);
    }

    @GetMapping("/emissoes/usuario/{userId}/all")
    public List<Emissao> getEmissionsByUser(@PathVariable Long userId) {
        Usuario user = usuarioService.getUserById(userId);
        return emissaoService.getEmissionsByUser(user.getId());
    }
}