package nature.calculadoraco22.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tree_plantings")
public class TreePlanting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numberOfTrees;

    @Column(nullable = false)
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "app_user", nullable = false)
    private User user;

    // Este campo será utilizado para armazenar o CO2 evitado
    @Column(nullable = false)
    private double CO2Avoided;

    public TreePlanting(int numberOfTrees, double CO2Avoided) {
        this.numberOfTrees = numberOfTrees;
        this.CO2Avoided = CO2Avoided;
    }

    public TreePlanting() {

    }
}
