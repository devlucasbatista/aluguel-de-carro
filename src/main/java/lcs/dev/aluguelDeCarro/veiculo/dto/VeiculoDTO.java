package lcs.dev.aluguelDeCarro.veiculo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO: objeto usado para trafegar dados de Veiculo nas requisições/respostas da API (não é a entidade do banco)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@NoArgsConstructor // gera construtor vazio (exigido para desserialização JSON)
@Data // gera getters, setters, equals, hashCode e toString
public class VeiculoDTO {

    private Long id;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @NotBlank(message = "Placa é obrigatória")
    @Pattern(
            regexp = "[A-Z]{3}\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}",
            message = "Placa deve estar no formato antigo (ABC1234) ou Mercosul (ABC1D23)"
    )
    private String placa;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @Min(value = 1950, message = "Ano deve ser maior que 1950")
    @Max(value = 2030, message = "Ano não pode ser maior que 2030")
    private int ano;

    private boolean disponivel;

    @NotNull(message = "Valor da diária é obrigatório")
    @Positive(message = "Valor da diária deve ser maior que zero")
    private Double valorDiaria;

    @NotNull(message = "Id do proprietário é obrigatório")
    private Long proprietarioId;
}