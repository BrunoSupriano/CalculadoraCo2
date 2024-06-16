package nature.calculadoraco2.Model;
import jakarta.persistence.*;

@Entity
public class Emissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AtividadedoUsuario atividadedoUsuario;

    private double totalEmissao;

    public Emissao() {
    }

    public Emissao(AtividadedoUsuario atividadedoUsuario, double totalEmissao) {
        this.atividadedoUsuario = atividadedoUsuario;
        this.totalEmissao = totalEmissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtividadedoUsuario getAtividadedoUsuario() {
        return atividadedoUsuario;
    }

    public void setAtividadedoUsuario(AtividadedoUsuario atividadedoUsuario) {
        this.atividadedoUsuario = atividadedoUsuario;
    }

    public double getTotalEmissao() {
        return totalEmissao;
    }

    public void setTotalEmissao(double totalEmissao) {
        this.totalEmissao = totalEmissao;
    }
}
