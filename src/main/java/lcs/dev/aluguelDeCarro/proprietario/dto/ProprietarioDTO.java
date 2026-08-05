package lcs.dev.aluguelDeCarro.proprietario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO: objeto usado para trafegar dados de Proprietario nas requisições/respostas da API (não é a entidade do banco)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@NoArgsConstructor // gera construtor vazio (exigido para desserialização JSON)
@Data // gera getters, setters, equals, hashCode e toString
public class ProprietarioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    private String sexo;
    private int idade;

}
