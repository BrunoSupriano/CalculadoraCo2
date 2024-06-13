package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<Atividade> criarAtividade(@RequestBody Atividade atividade) {
        Atividade novaAtividade = atividadeService.criarAtividade(atividade);
        return new ResponseEntity<>(novaAtividade, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listarAtividades() {
        List<Atividade> atividades = atividadeService.listarTodas();
        return new ResponseEntity<>(atividades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarAtividadePorId(@PathVariable Long id) {
        Atividade atividade = atividadeService.buscarPorId(id);
        return atividade != null ?
                new ResponseEntity<>(atividade, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Long id) {
        atividadeService.deletarAtividade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
