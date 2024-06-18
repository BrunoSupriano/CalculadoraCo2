package nature.calculadoraco2.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
