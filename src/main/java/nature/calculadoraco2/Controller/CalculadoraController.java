package nature.calculadoraco2.Controller;

import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Model.AtividadedoUsuario;
import nature.calculadoraco2.Model.Emissao;
import nature.calculadoraco2.Model.Usuario;
import nature.calculadoraco2.Service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CalculadoraController {

    private final AtividadeService carbonCalculatorService;

    @Autowired
    public CalculadoraController(AtividadeService carbonCalculatorService) {
        this.carbonCalculatorService = carbonCalculatorService;
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Atividade>> getAllActivities() {
        List<Atividade> activities = AtividadeService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<Atividade> getActivityById(@PathVariable Long id) {
        Atividade activity = AtividadeService.getActivityById(id);
        if (activity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        User user = AtividadeService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user-activities")
    public ResponseEntity<AtividadedoUsuario> createUserActivity(@RequestBody AtividadedoUsuario userActivity) {
        AtividadedoUsuario createdUserActivity = AtividadeService.createUserActivity(userActivity);
        return new ResponseEntity<>(createdUserActivity, HttpStatus.CREATED);
    }

    @PostMapping("/emissions")
    public ResponseEntity<Emissao> calculateEmissions(@RequestBody AtividadedoUsuario userActivity) {
        Emissao emission = AtividadeService.calculateEmissions(userActivity);
        return new ResponseEntity<>(emission, HttpStatus.CREATED);
    }

    @GetMapping("/emissions/user/{id}")
    public ResponseEntity<List<Emissao>> getEmissionsByUser(@PathVariable Long id) {
        Usuario user = AtividadeService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Emissao> emissions = AtividadeService.getEmissionsByUser(user);
        return new ResponseEntity<>(emissions, HttpStatus.OK);
    }
}
