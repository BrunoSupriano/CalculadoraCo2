package nature.calculadoraco2.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.dsig.Manifest;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor

@Entity

public class CalculoEmissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mes;
    private String totalEmissao;
    private String totalEmissaoUsuario;
}