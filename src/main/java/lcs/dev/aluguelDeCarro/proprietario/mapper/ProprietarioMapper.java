package lcs.dev.aluguelDeCarro.proprietario.mapper;

import lcs.dev.aluguelDeCarro.proprietario.dto.ProprietarioDTO;
import lcs.dev.aluguelDeCarro.proprietario.model.ProprietarioModel;
import org.springframework.stereotype.Component;

// Componente responsável por converter ProprietarioModel (entidade) <-> ProprietarioDTO (objeto de transporte)
@Component
public class ProprietarioMapper {

    // Converte a entidade (vinda do banco) para o DTO (enviado na resposta da API)
    public ProprietarioDTO toDTO(ProprietarioModel model) {
        if (model == null) {
            return null;
        }

        return new ProprietarioDTO(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getEmail(),
                model.getEndereco(),
                model.getTelefone(),
                model.getSexo(),
                model.getIdade()
        );
    }

    // Converte o DTO (recebido na requisição) para a entidade (para salvar no banco)
    public ProprietarioModel toModel(ProprietarioDTO dto) {
        if (dto == null) {
            return null;
        }

        return new ProprietarioModel(
                dto.getId(),
                dto.getNome(),
                dto.getCpf(),
                dto.getEmail(),
                dto.getEndereco(),
                dto.getTelefone(),
                dto.getSexo(),
                dto.getIdade()
        );
    }
}
