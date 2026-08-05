package lcs.dev.aluguelDeCarro.cliente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // gera construtor vazio (exigido pelo JPA)
@AllArgsConstructor // gera construtor com todos os campos (usado pelo mapper)
@Data // gera getters, setters, equals, hashCode e toString
@Entity // marca a classe como entidade JPA (mapeada para uma tabela)
@Table(name = "tb_cliente") // define o nome da tabela no banco
public class ClienteModel {

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id gerado automaticamente pelo banco (auto increment)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false) // cpf não pode repetir e é obrigatório
    private String cpf;

    @Column(unique = true) // email não pode repetir
    private String email;

    @Column(nullable = false) // endereço é obrigatório
    private String endereco;

    @Column(unique = true) // telefone não pode repetir
    private String telefone;

    private String sexo;

    private String cnh;

    private int idade;
}
