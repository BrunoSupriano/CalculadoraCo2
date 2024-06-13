package nature.calculadoraco2.Repositories;

import nature.calculadoraco2.Model.AtividadedoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadedoUsuarioRepository extends JpaRepository<AtividadedoUsuario, Integer> {
}
