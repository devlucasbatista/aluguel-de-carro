package lcs.dev.aluguelDeCarro.aluguel.dto;

import lcs.dev.aluguelDeCarro.aluguel.enums.AluguelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// DTO: objeto usado para trafegar dados de Aluguel nas requisições/respostas da API (não é a entidade do banco)
@NoArgsConstructor // gera construtor vazio (exigido para desserialização JSON)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@Data // gera getters, setters, equals, hashCode e toString
public class AluguelDTO {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valorTotal;
    private AluguelStatus status;
    private Long clienteId; // id do cliente relacionado (evita expor a entidade inteira)
    private Long veiculoId; // id do veículo relacionado (evita expor a entidade inteira)
}
