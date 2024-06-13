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
    private Atividade activity;
    private double quantity;

    public AtividadedoUsuario() {

    }
    // Construtores, getters e setters

    public Long getId() {
        return id;
    }

    public AtividadedoUsuario(Long id, Usuario usuario, Atividade activity, double quantity) {
        this.id = id;
        this.usuario = usuario;
        this.activity = activity;
        this.quantity = quantity;
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

    public Atividade getActivity() {
        return activity;
    }

    public void setActivity(Atividade activity) {
        this.activity = activity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}