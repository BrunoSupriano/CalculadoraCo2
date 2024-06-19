package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Dto.UsuarioDto;
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
    @ResponseStatus(HttpStatus.OK)
    public List<AtividadeDto> getAllAtividades() {
            return atividadeService.getAllAtividades();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<AtividadedoUsuario> criarAtividade() {
        AtividadedoUsuario atividadedoUsuario = new AtividadedoUsuario();
        // Set the properties of atividadedoUsuario based on createdActivity
        // ...
        return new ResponseEntity<>(atividadedoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/atividades/{id}")
    public AtividadeDto getActivityById(@PathVariable Long id) {
        return atividadeService.getAtividadeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Long id) {
        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }

}