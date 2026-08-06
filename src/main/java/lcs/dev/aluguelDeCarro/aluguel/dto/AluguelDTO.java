package lcs.dev.aluguelDeCarro.aluguel.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Data de início é obrigatória")
    @FutureOrPresent(message = "Data de início não pode ser no passado")
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    private LocalDate dataFim;

    private Double valorTotal; // calculado pelo Service, não precisa validar aqui

    private AluguelStatus status;

    @NotNull(message = "Id do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Id do veículo é obrigatório")
    private Long veiculoId;
}