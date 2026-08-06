package lcs.dev.aluguelDeCarro.cliente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO: objeto usado para trafegar dados de Cliente nas requisições/respostas da API (não é a entidade do banco)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@NoArgsConstructor // gera construtor vazio (exigido para desserialização JSON)
@Data // gera getters, setters, equals, hashCode e toString
public class ClienteDTO {

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

    @NotBlank(message = "CNH é obrigatória")
    private String cnh;

    @Min(value = 18, message = "Cliente deve ser maior de idade")
    private int idade;
}