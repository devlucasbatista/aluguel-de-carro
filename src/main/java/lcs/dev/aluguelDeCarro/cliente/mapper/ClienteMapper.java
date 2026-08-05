package lcs.dev.aluguelDeCarro.cliente.mapper;

import lcs.dev.aluguelDeCarro.cliente.dto.ClienteDTO;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import org.springframework.stereotype.Component;

// Componente responsável por converter ClienteModel (entidade) <-> ClienteDTO (objeto de transporte)
@Component
public class ClienteMapper {

    // Converte a entidade (vinda do banco) para o DTO (enviado na resposta da API)
    public ClienteDTO toDTO(ClienteModel model) {
        if (model == null) {
            return null;
        }

        return new ClienteDTO(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getEmail(),
                model.getEndereco(),
                model.getTelefone(),
                model.getSexo(),
                model.getCnh(),
                model.getIdade()
        );
    }

    // Converte o DTO (recebido na requisição) para a entidade (para salvar no banco)
    public ClienteModel toModel(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ClienteModel(
                dto.getId(),
                dto.getNome(),
                dto.getCpf(),
                dto.getEmail(),
                dto.getEndereco(),
                dto.getTelefone(),
                dto.getSexo(),
                dto.getCnh(),
                dto.getIdade()

        );
    }
}
