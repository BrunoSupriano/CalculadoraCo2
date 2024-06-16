package nature.calculadoraco2.Repositories;

import java.util.List;
import nature.calculadoraco2.Model.Emissao;
import nature.calculadoraco2.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissaoRepository extends JpaRepository<Emissao, Long> {
    List<Emissao> findByAtividadedoUsuarioUsuario(Usuario usuario);
}
