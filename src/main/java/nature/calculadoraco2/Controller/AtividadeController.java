package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.AtividadedoUsuario;
import nature.calculadoraco2.Service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Atividade> listarTodas() {
        return atividadeService.getAllActivities();
    }

    @GetMapping("/{id}")
    public Atividade buscarPorId(@PathVariable Long id) {
        return atividadeService.getActivityById(id);
    }

    @PostMapping("/criar")
    public ResponseEntity<AtividadedoUsuario> criarAtividade(@RequestBody AtividadedoUsuario userActivity) {
        AtividadedoUsuario createdActivity = atividadeService.createUserActivity(userActivity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Long id) {
        atividadeService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}