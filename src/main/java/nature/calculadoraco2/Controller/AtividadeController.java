package nature.calculadoraco2.Controller;
import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/atividade")


public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AtividadeDto> getAllAtividades() {
            return atividadeService.getAllAtividades();
        }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AtividadeDto addAtividade(@RequestBody AtividadeDto dto) {
        return atividadeService.saveAtividade(dto);
    }

    @GetMapping("/usuario/{Id}")
    public List<AtividadeDto> getAtividadeById(@PathVariable Long Id) {
        return Collections.singletonList(atividadeService.getAtividadeById(Id));
        // era return atividadeService.getAtividadeById(Id); mas o método getAtividadeById não retorna
    }
}