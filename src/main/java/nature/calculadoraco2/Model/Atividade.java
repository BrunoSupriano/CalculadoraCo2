package nature.calculadoraco2.Model;
import jakarta.persistence.*;

@Entity

public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double emissionFactor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }

    public void setEmissionFactor(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }
}
