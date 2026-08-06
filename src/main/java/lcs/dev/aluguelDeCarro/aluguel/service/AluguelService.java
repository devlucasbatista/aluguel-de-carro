package lcs.dev.aluguelDeCarro.aluguel.service;

import lcs.dev.aluguelDeCarro.aluguel.dto.AluguelDTO;
import lcs.dev.aluguelDeCarro.aluguel.enums.AluguelStatus;
import lcs.dev.aluguelDeCarro.aluguel.mapper.AluguelMapper;
import lcs.dev.aluguelDeCarro.aluguel.model.AluguelModel;
import lcs.dev.aluguelDeCarro.aluguel.repository.AluguelRepository;
import lcs.dev.aluguelDeCarro.exceptions.AluguelNotFoundException;
import lcs.dev.aluguelDeCarro.exceptions.PeriodoInvalidoException;
import lcs.dev.aluguelDeCarro.exceptions.VeiculoIndisponivelException;
import lcs.dev.aluguelDeCarro.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

// Camada de serviço: concentra a regra de negócio de Aluguel entre o controller e o repositório
@Service
public class AluguelService {

    // INJEÇÃO DE DEPENDENCIA
    private final AluguelRepository aluguelRepository;
    private final AluguelMapper aluguelMapper;
    private final VeiculoRepository veiculoRepository;

    // Injeção de dependência via construtor


    public AluguelService(AluguelRepository aluguelRepository, AluguelMapper aluguelMapper, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.aluguelMapper = aluguelMapper;
        this.veiculoRepository = veiculoRepository;
    }

    // SALVAR ALUGUEL
    // Converte o DTO em entidade, calcula o valor total pela quantidade de dias x diária do veículo e persiste
    public AluguelDTO salvarAluguel(AluguelDTO aluguelDTO) {
        AluguelModel aluguelModel = aluguelMapper.toModel(aluguelDTO);

        if (aluguelModel.getDataFim().isBefore(aluguelModel.getDataInicio())) {
            throw new PeriodoInvalidoException();
        }

        if (!aluguelModel.getVeiculo().isDisponivel()) {
            throw new VeiculoIndisponivelException(aluguelModel.getVeiculo().getId());
        }

        long dias = ChronoUnit.DAYS.between(aluguelModel.getDataInicio(), aluguelModel.getDataFim());
        double valorTotal = dias * aluguelModel.getVeiculo().getValorDiaria();
        aluguelModel.setValorTotal(valorTotal);
        aluguelModel.setStatus(AluguelStatus.ATIVO);

        aluguelModel.getVeiculo().setDisponivel(false);
        veiculoRepository.save(aluguelModel.getVeiculo());

        return aluguelMapper.toDTO(aluguelRepository.save(aluguelModel));
    }

    // LISTAR ALUGUEL
    // Busca todos os registros e converte cada entidade em DTO
    public List<AluguelDTO> listarAluguel() {
        List<AluguelModel> aluguelModels = aluguelRepository.findAll();
        return aluguelModels.stream()
                .map(aluguelMapper::toDTO)
                .collect(Collectors.toList());
    }

    // LISTAR ALUGUEL POR ID
    // Lança AluguelNotFoundException se o id não existir

    public AluguelDTO consultarAluguelPorId(Long id) {
        AluguelModel aluguelModel = aluguelRepository.findById(id)
                .orElseThrow(() -> new AluguelNotFoundException(id));
        return aluguelMapper.toDTO(aluguelModel);
    }

    // ATUALIZAR ALUGUEL POR ID
    // Lança AluguelNotFoundException se o id não existir; senão recalcula o valor total e salva

    public AluguelDTO atualizarAluguelPorId(Long id, AluguelDTO aluguelDTO) {
        if (!aluguelRepository.existsById(id)) {
            throw new AluguelNotFoundException(id);
        }
        AluguelModel aluguelAtualizado = aluguelMapper.toModel(aluguelDTO);
        aluguelAtualizado.setId(id);

        if (aluguelAtualizado.getDataFim().isBefore(aluguelAtualizado.getDataInicio())) {
            throw new PeriodoInvalidoException();
        }

        long dias = ChronoUnit.DAYS.between(aluguelAtualizado.getDataInicio(), aluguelAtualizado.getDataFim());
        double valorTotal = dias * aluguelAtualizado.getVeiculo().getValorDiaria();
        aluguelAtualizado.setValorTotal(valorTotal);
        return aluguelMapper.toDTO(aluguelRepository.save(aluguelAtualizado));
    }

    // DELETAR ALUGUEL
    public void deleteAluguelPorId(Long id) {
        if (!aluguelRepository.existsById(id)) {
            throw new AluguelNotFoundException(id);
        }
        aluguelRepository.deleteById(id);
    }

    // FINALIZAR ALUGUEL
    public AluguelDTO finalizarAluguel(Long id) {
        AluguelModel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new AluguelNotFoundException(id)); // 1º: verifica se existe

        aluguel.setStatus(AluguelStatus.FINALIZADO); // 2º: só chega aqui se existir
        aluguel.getVeiculo().setDisponivel(true);
        veiculoRepository.save(aluguel.getVeiculo());

        return aluguelMapper.toDTO(aluguelRepository.save(aluguel));
    }
}