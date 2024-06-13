package nature.calculadoraco2.Repositories;

import nature.calculadoraco2.Model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
}
