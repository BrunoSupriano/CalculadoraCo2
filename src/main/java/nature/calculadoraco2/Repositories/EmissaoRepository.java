package nature.calculadoraco2.Repositories;

import nature.calculadoraco2.Model.Emissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissaoRepository extends JpaRepository<Emissao, Long> {
}
