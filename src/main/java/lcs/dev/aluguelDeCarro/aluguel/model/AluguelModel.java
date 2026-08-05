package lcs.dev.aluguelDeCarro.aluguel.model;

import jakarta.persistence.*;
import lcs.dev.aluguelDeCarro.aluguel.enums.AluguelStatus;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_aluguel")
public class AluguelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_alugado")
    private VeiculoModel veiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_alugou")
    private ClienteModel cliente;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private double valorTotal;

    @Enumerated(EnumType.STRING)
    private AluguelStatus status;

}
