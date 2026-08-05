package lcs.dev.aluguelDeCarro.veiculo.model;

import jakarta.persistence.*;
import lcs.dev.aluguelDeCarro.proprietario.model.ProprietarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // gera construtor vazio (exigido pelo JPA)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@Data // gera getters, setters, equals, hashCode e toString
@Entity // marca a classe como entidade JPA (mapeada para uma tabela)
@Table(name = "tb_veiculo") // define o nome da tabela no banco
public class VeiculoModel {

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id gerado automaticamente pelo banco (auto increment)
    private Long id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @Column(unique = true) // placa não pode repetir
    private String placa;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private boolean disponivel;

    @Column(nullable = false)
    private Double valorDiaria;

    // relacionamento N:1 -> vários veículos podem pertencer a um mesmo proprietário
    @ManyToOne
    @JoinColumn(name = "proprietario_id") // coluna de chave estrangeira na tabela tb_veiculo
    private ProprietarioModel proprietario;
}
