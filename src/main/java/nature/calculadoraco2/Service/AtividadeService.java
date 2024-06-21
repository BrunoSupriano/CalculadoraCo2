package nature.calculadoraco2.Service;
import nature.calculadoraco2.Dto.AtividadeDto;
import nature.calculadoraco2.Mapper.AtividadeMapper;
import nature.calculadoraco2.Model.Atividade;
import nature.calculadoraco2.Repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public AtividadeDto getAtividadeById(Long id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (atividade.isEmpty()) throw new RuntimeException("A atividade não existe");
        return atividadeMapper.toDto(atividade.get());
    }

    public AtividadeDto saveAtividade(AtividadeDto atividadeDto) {
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeDto.nome());
        atividadeRepository.save(atividade);
        return atividadeMapper.toDto(atividade);
    }


    // não usado, a implementar...
    public AtividadeDto updateAtividade(Long id, AtividadeDto atividadeDto) {
        Atividade atividade = atividadeRepository.findById(id).get();
        atividade.setNome(atividadeDto.nome());
        atividadeRepository.save(atividade);
        return atividadeMapper.toDto(atividade);
    }

    public AtividadeDto deleteAtividade(Long id) {
        Atividade atividade = atividadeRepository.findById(id).get();
        atividadeRepository.delete(atividade);
        return atividadeMapper.toDto(atividade);
    }
}