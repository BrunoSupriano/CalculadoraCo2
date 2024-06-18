package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.AtividadedoUsuario;
import nature.calculadoraco2.Service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Atividade> listarTodas() {
        return atividadeService.getAllAtividades().stream()
            .map(atividadeDto -> {
                // Set the properties of atividade based on atividadeDto
                // ...
                return new Atividade();
            })
            .collect(Collectors.toList());
    }

    @PostMapping("/criar")
    public ResponseEntity<AtividadedoUsuario> criarAtividade() {
        AtividadedoUsuario atividadedoUsuario = new AtividadedoUsuario();
        // Set the properties of atividadedoUsuario based on createdActivity
        // ...
        return new ResponseEntity<>(atividadedoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/atividades/{id}")
    public Atividade getActivityById(@PathVariable Integer id) {
        return atividadeService.getAtividadeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Integer id) {
        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }

}