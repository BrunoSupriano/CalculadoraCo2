package nature.calculadoraco2.Service;
import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Mapper.AtividadeMapper;
import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;
    @Autowired
    private AtividadeMapper atividadeMapper;

    public List<AtividadeDto> getAllAtividades() {
        List<Atividade> listaAtividades = atividadeRepository.findAll();
        return listaAtividades.stream().map(atividadeMapper::toDto).toList();
    }

    public Atividade getAtividadeById(Integer id) {
        return atividadeRepository.findById(id).orElse(null);
    }

    public AtividadeDto saveAtividade(AtividadeDto atividadeDto) {
        Atividade atividade = new Atividade();
        atividade.setName(atividadeDto.name());
        atividade.setEmissionFactor(atividadeDto.emissionFactor());
        atividadeRepository.save(atividade);
        return atividadeMapper.toDto(atividade);
    }

    public AtividadeDto updateAtividade(Integer id, AtividadeDto atividadeDto) {
        Atividade atividade = atividadeRepository.findById(id).get();
        atividade.setName(atividadeDto.name());
        atividade.setEmissionFactor(atividadeDto.emissionFactor());
        atividadeRepository.save(atividade);
        return atividadeMapper.toDto(atividade);
    }

    public AtividadeDto deleteAtividade(Integer id) {
        Atividade atividade = atividadeRepository.findById(id).get();
        atividadeRepository.delete(atividade);
        return atividadeMapper.toDto(atividade);
    }
}