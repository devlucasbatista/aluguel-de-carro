package lcs.dev.aluguelDeCarro.aluguel.dto;

import lcs.dev.aluguelDeCarro.aluguel.enums.AluguelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AluguelDTO {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valorTotal;
    private AluguelStatus status;
    private Long clienteId;
    private Long veiculoId;
}
