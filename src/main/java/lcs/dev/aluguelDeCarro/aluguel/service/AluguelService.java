package lcs.dev.aluguelDeCarro.aluguel.service;

import lcs.dev.aluguelDeCarro.aluguel.dto.AluguelDTO;
import lcs.dev.aluguelDeCarro.aluguel.mapper.AluguelMapper;
import lcs.dev.aluguelDeCarro.aluguel.model.AluguelModel;
import lcs.dev.aluguelDeCarro.aluguel.repository.AluguelRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Camada de serviço: concentra a regra de negócio de Aluguel entre o controller e o repositório
@Service
public class AluguelService {

    // INJEÇÃO DE DEPENDENCIA
    private final AluguelRepository aluguelRepository;
    private final AluguelMapper aluguelMapper;


    // Injeção de dependência via construtor
    public AluguelService(AluguelRepository aluguelRepository, AluguelMapper aluguelMapper) {
        this.aluguelRepository = aluguelRepository;
        this.aluguelMapper = aluguelMapper;
    }


    // SALVAR ALUGUEL
    // Converte o DTO em entidade, calcula o valor total pela quantidade de dias x diária do veículo e persiste
    public AluguelDTO salvarAluguel(AluguelDTO aluguelDTO) {
        AluguelModel aluguelModel = aluguelMapper.toModel(aluguelDTO);
        long dias = ChronoUnit.DAYS.between(aluguelModel.getDataInicio(), aluguelModel.getDataFim());
        double valorTotal = dias * aluguelModel.getVeiculo().getValorDiaria();
        aluguelModel.setValorTotal(valorTotal);
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
    // Retorna null (tratado no controller) se não encontrar
    public AluguelDTO consultarAluguelPorId(Long id) {
        return aluguelMapper.toDTO(aluguelRepository.findById(id).orElse(null));
    }
    // ATUALIZAR ALUGUEL POR ID
    // Recalcula o valor total com base nas novas datas/veículo antes de salvar novamente
    public AluguelDTO atualizarAluguelPorId(Long id,  AluguelDTO aluguelDTO) {
        if (aluguelRepository.existsById(id)) {
            AluguelModel aluguelAtualizado = aluguelMapper.toModel(aluguelDTO);
            aluguelAtualizado.setId(id);
            long dias = ChronoUnit.DAYS.between(aluguelAtualizado.getDataInicio(), aluguelAtualizado.getDataFim());
            double valorTotal = dias * aluguelAtualizado.getVeiculo().getValorDiaria();
            aluguelAtualizado.setValorTotal(valorTotal);
            return aluguelMapper.toDTO(aluguelRepository.save(aluguelAtualizado));
        }
        return null; // id não encontrado
    }
    // DELETAR ALUGUEL
    public void deleteAluguelPorId(Long id) {
        aluguelRepository.deleteById(id);
    }

}
