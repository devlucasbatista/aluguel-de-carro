package lcs.dev.aluguelDeCarro.aluguel.model;

import jakarta.persistence.*;
import lcs.dev.aluguelDeCarro.aluguel.enums.AluguelStatus;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor // gera construtor vazio (exigido pelo JPA)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@Data // gera getters, setters, equals, hashCode e toString
@Entity // marca a classe como entidade JPA (mapeada para uma tabela)
@Table(name = "tb_aluguel") // define o nome da tabela no banco
public class AluguelModel {

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id gerado automaticamente pelo banco (auto increment)
    private Long id;

    @ManyToOne // vários aluguéis podem estar ligados ao mesmo veículo
    @JoinColumn(name = "veiculo_alugado") // coluna de chave estrangeira para o veículo alugado
    private VeiculoModel veiculo;

    @ManyToOne // vários aluguéis podem estar ligados ao mesmo cliente
    @JoinColumn(name = "cliente_alugou") // coluna de chave estrangeira para o cliente que alugou
    private ClienteModel cliente;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private double valorTotal; // calculado a partir dos dias de aluguel x valor da diária do veículo

    @Enumerated(EnumType.STRING) // grava o status como texto no banco (mais legível que ordinal)
    private AluguelStatus status;

}
