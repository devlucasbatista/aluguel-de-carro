package lcs.dev.aluguelDeCarro.proprietario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO: objeto usado para trafegar dados de Proprietario nas requisições/respostas da API (não é a entidade do banco)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@NoArgsConstructor // gera construtor vazio (exigido para desserialização JSON)
@Data // gera getters, setters, equals, hashCode e toString
public class ProprietarioDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "Sexo é obrigatório")
    private String sexo;


    @Min(value = 18, message = "Proprietario deve ser maior de idade")
    private int idade;
}
