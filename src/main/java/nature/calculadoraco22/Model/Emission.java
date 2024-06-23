package nature.calculadoraco22.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Emission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activity;
    private Double co2;

    @Column(name = "emission_month", nullable = false)
    private int month;

    @Column(name = "emission_year", nullable = false)
    private int year;

    @ManyToOne
    @JoinColumn(name = "app_user", nullable = false)
    @JsonBackReference
    private User user;

}
