package lcs.dev.aluguelDeCarro.veiculo.mapper;

import lcs.dev.aluguelDeCarro.proprietario.model.ProprietarioModel;
import lcs.dev.aluguelDeCarro.proprietario.repository.ProprietarioRepository;
import lcs.dev.aluguelDeCarro.veiculo.dto.VeiculoDTO;
import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import org.springframework.stereotype.Component;

// Componente responsável por converter VeiculoModel (entidade) <-> VeiculoDTO (objeto de transporte)
@Component
public class VeiculoMapper {

    // Repositório usado para buscar o Proprietario completo a partir do id vindo do DTO
    private final ProprietarioRepository proprietarioRepository;

    // Injeção de dependência via construtor
    public VeiculoMapper(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    // Converte a entidade (vinda do banco) para o DTO (enviado na resposta da API)
    public VeiculoDTO toDTO(VeiculoModel model) {
        if (model == null) {
            return null;
        }

        return new VeiculoDTO(
                model.getId(),
                model.getModelo(),
                model.getMarca(),
                model.getPlaca(),
                model.getCor(),
                model.getAno(),
                model.isDisponivel(),
                model.getValorDiaria(),
                // extrai apenas o id do proprietário; evita null se o veículo não tiver proprietário vinculado
                model.getProprietario() != null ? model.getProprietario().getId() : null
        );
    }

    // Converte o DTO (recebido na requisição) para a entidade (para salvar no banco)
    public VeiculoModel toModel(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }

        // busca o Proprietario real no banco a partir do id informado no DTO; null se não existir
        ProprietarioModel proprietario = proprietarioRepository.findById(dto.getProprietarioId()).orElse(null);

        return new VeiculoModel(
                dto.getId(),
                dto.getModelo(),
                dto.getMarca(),
                dto.getPlaca(),
                dto.getCor(),
                dto.getAno(),
                dto.isDisponivel(),
                dto.getValorDiaria(),
                proprietario
        );
    }
}
