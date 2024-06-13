package nature.calculadoraco2.Model;
import jakarta.persistence.*;

@Entity

public class Emissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AtividadedoUsuario userActivity;

    private double totalEmission;

    // Construtores, getters e setters

    public Emissao() {
    }

    public Emissao(AtividadedoUsuario userActivity, double totalEmission) {
        this.userActivity = userActivity;
        this.totalEmission = totalEmission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtividadedoUsuario getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(AtividadedoUsuario userActivity) {
        this.userActivity = userActivity;
    }

    public double getTotalEmission() {
        return totalEmission;
    }

    public void setTotalEmission(double totalEmission) {
        this.totalEmission = totalEmission;
    }
}
