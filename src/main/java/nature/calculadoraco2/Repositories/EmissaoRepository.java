package nature.calculadoraco2.Repositories;
import nature.calculadoraco2.Model.Emissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Repository
public interface EmissaoRepository extends JpaRepository<Emissao, Long> {
    List<Emissao> findAllByMesAndAnoAndUsuarioId(Month mes, Year ano, Long usuarioId);
}
