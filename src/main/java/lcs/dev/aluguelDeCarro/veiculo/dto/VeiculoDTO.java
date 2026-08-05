package lcs.dev.aluguelDeCarro.veiculo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO: objeto usado para trafegar dados de Veiculo nas requisições/respostas da API (não é a entidade do banco)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@NoArgsConstructor // gera construtor vazio (exigido para desserialização JSON)
@Data // gera getters, setters, equals, hashCode e toString
public class VeiculoDTO {



    private Long id;
    private String modelo;
    private String marca;
    private String placa;
    private String cor;
    private int ano;
    private boolean disponivel;
    private Double valorDiaria;
    private Long proprietarioId; // apenas o id do proprietário (não o objeto completo)

}
