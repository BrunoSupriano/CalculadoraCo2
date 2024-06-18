package nature.calculadoraco2.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Usuario() {

    }

    public void setNome(String novoUsuario) {
        this.name = novoUsuario;
    }

    public String getNome() {
        return this.name;
    }
}