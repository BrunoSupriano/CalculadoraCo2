package nature.calculadoraco2.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nature.calculadoraco2.Dto.AtividadeDto;

@Setter
@Getter
@Entity
public class AtividadedoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Atividade atividade;

    private double quantidade;

    public AtividadedoUsuario(Long id, Usuario usuario, Atividade atividade, double quantidade) {
        this.id = id;
        this.usuario = usuario;
        this.atividade = atividade;
        this.quantidade = quantidade;
    }

    public AtividadedoUsuario(AtividadeDto createdActivity) {
    }

    public AtividadedoUsuario() {

    }

}