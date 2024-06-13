package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Model.Emissao;
import nature.calculadoraco2.Service.EmissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emissao") // Adicionei um mapeamento base para as rotas
public class EmissaoController {

    @Autowired
    private EmissaoService emissaoService;

    @PostMapping("/calcular")
    public ResponseEntity<Emissao> calcularEmissao(@RequestBody Emissao emissao) {
        Emissao emissaoCalculada = emissaoService.calcularEmissao(emissao);
        return new ResponseEntity<>(emissaoCalculada, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Emissao>> listarEmissaoPorUsuario(@PathVariable Long usuarioId) {
        List<Emissao> emissoes = emissaoService.listarEmissaoPorUsuario(usuarioId);
        return new ResponseEntity<>(emissoes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emissao> atualizarEmissao(@PathVariable Long id, @RequestBody Emissao emissao) {
        Emissao emissaoAtualizada = emissaoService.atualizarEmissao(id, emissao);
        return emissaoAtualizada != null ?
                new ResponseEntity<>(emissaoAtualizada, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmissao(@PathVariable Long id) {
        emissaoService.deletarEmissao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
