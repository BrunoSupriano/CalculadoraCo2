package nature.calculadoraco2.Repositories;
import nature.calculadoraco2.Model.CalculoEmissao;
import nature.calculadoraco2.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmissaoRepository extends JpaRepository<CalculoEmissao, Long> {
    List<CalculoEmissao> findByAtividade(Usuario usuario);
}
