package nature.calculadoraco2.Controller;
import jakarta.validation.Valid;
import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("{userid}/atividades")


public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AtividadeDto> getAllAtividades() {
            return atividadeService.getAllAtividades();
        }


    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public AtividadeDto saveAtividade(@RequestBody @Valid AtividadeDto atividadeDto) {
        return atividadeService.saveAtividade(atividadeDto);
    }

    @GetMapping("/atividades/{id}")
    public AtividadeDto getAtividadeById(@PathVariable Long id) {
        return atividadeService.getAtividadeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }

}