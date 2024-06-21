package nature.calculadoraco2.Service;
import nature.calculadoraco2.Dto.EmissaoDto;
import nature.calculadoraco2.Mapper.EmissaoMapper;
import nature.calculadoraco2.Model.Emissao;

import java.time.Month;
import java.time.Year;
import java.util.List;

import nature.calculadoraco2.Repositories.EmissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoService {

    @Autowired
    private EmissaoRepository repository;

    @Autowired
    private EmissaoMapper mapper;

    public EmissaoDto saveEmissao(EmissaoDto dto) {
        Emissao emissao = mapper.toEntity(dto);
        emissao = repository.save(emissao);
        return mapper.toDto(emissao);
    }

    public double getTotalEmissionsByMonthAndUser(Month mes, Year ano, Long usuarioId) {
        List<Emissao> emissoes = repository.findAllByMesAndAnoAndUsuarioId(mes, ano, usuarioId);
        return emissoes.stream().mapToDouble(Emissao::getTotalCo2).sum();
    }
}