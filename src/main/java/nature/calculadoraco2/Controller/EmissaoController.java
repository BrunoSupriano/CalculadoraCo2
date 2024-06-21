package nature.calculadoraco2.Controller;
import nature.calculadoraco2.Dto.EmissaoDto;
import nature.calculadoraco2.Service.EmissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/emissoes")
public class EmissaoController {

    @Autowired
    private EmissaoService emissaoService;


    @PostMapping
    public EmissaoDto addEmissao(@RequestBody EmissaoDto dto) {
        return emissaoService.saveEmissao(dto);
    }

    @GetMapping("/total")
    public double getTotalEmissions(@RequestParam Month mes, @RequestParam Year ano, @RequestParam Long usuarioId) {
        return emissaoService.getTotalEmissionsByMonthAndUser(mes, ano, usuarioId);
    }
}
