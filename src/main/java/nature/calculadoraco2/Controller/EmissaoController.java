package nature.calculadoraco2.Controller;
import nature.calculadoraco2.Service.EmissaoService;
import nature.calculadoraco2.Model.Emissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/emissoes")
public class EmissaoController {

    @Autowired
    private EmissaoService emissaoService;

    @PostMapping("/calcular")
    public ResponseEntity<Emissao> totalEmissao(@RequestBody Emissao emission) {
        Emissao calculatedEmission = emissaoService.calculateEmission(emission);
        return new ResponseEntity<>(calculatedEmission, HttpStatus.CREATED);
    }


    //Usuario
    @GetMapping("/usuario/{userId}")
    public List<Emissao> listarEmissaoPorUsuario(@PathVariable Long userId) {
        return emissaoService.getEmissionsByUser(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emissao> atualizarEmissao(@PathVariable Long id, @RequestBody Emissao emission) {
        Emissao updatedEmission = emissaoService.updateEmission(id, emission);
        return ResponseEntity.ok(updatedEmission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmissao(@PathVariable Long id) {
        emissaoService.deleteEmission(id);
        return ResponseEntity.noContent().build();
    }
}
