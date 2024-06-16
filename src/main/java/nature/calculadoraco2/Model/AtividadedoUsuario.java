package nature.calculadoraco2.Model;
import jakarta.persistence.*;

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

    public AtividadedoUsuario() {
    }

    // Construtores, getters e setters

    public AtividadedoUsuario(Long id, Usuario usuario, Atividade atividade, double quantidade) {
        this.id = id;
        this.usuario = usuario;
        this.atividade = atividade;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Atividade getCorrectActivityMethod() {
        return this.atividade;
    }

    public double getCorrectQuantityMethod() {
        return this.quantidade;
    }
}