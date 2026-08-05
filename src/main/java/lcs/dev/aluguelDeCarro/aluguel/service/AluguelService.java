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

@Service
public class AluguelService {

    // INJEÇÃO DE DEPENDENCIA
    private final AluguelRepository aluguelRepository;
    private final AluguelMapper aluguelMapper;


    public AluguelService(AluguelRepository aluguelRepository, AluguelMapper aluguelMapper) {
        this.aluguelRepository = aluguelRepository;
        this.aluguelMapper = aluguelMapper;
    }


    // SALVAR ALUGUEL
    public AluguelDTO salvarAluguel(AluguelDTO aluguelDTO) {
        AluguelModel aluguelModel = aluguelMapper.toModel(aluguelDTO);
        long dias = ChronoUnit.DAYS.between(aluguelModel.getDataInicio(), aluguelModel.getDataFim());
        double valorTotal = dias * aluguelModel.getVeiculo().getValorDiaria();
        aluguelModel.setValorTotal(valorTotal);
        return aluguelMapper.toDTO(aluguelRepository.save(aluguelModel));
    }
    // LISTAR ALUGUEL
    public List<AluguelDTO> listarAluguel() {
        List<AluguelModel> aluguelModels = aluguelRepository.findAll();
        return aluguelModels.stream()
                .map(aluguelMapper::toDTO)
                .collect(Collectors.toList());
    }

    // LISTAR ALUGUEL POR ID
    public AluguelDTO consultarAluguelPorId(Long id) {
        return aluguelMapper.toDTO(aluguelRepository.findById(id).orElse(null));
    }
    // ATUALIZAR ALUGUEL POR ID
    public AluguelDTO atualizarAluguelPorId(Long id,  AluguelDTO aluguelDTO) {
        if (aluguelRepository.existsById(id)) {
            AluguelModel aluguelAtualizado = aluguelMapper.toModel(aluguelDTO);
            aluguelAtualizado.setId(id);
            long dias = ChronoUnit.DAYS.between(aluguelAtualizado.getDataInicio(), aluguelAtualizado.getDataFim());
            double valorTotal = dias * aluguelAtualizado.getVeiculo().getValorDiaria();
            aluguelAtualizado.setValorTotal(valorTotal);
            return aluguelMapper.toDTO(aluguelRepository.save(aluguelAtualizado));
        }
        return null;
    }
    // DELETAR ALUGUEL
    public void deleteAluguelPorId(Long id) {
        aluguelRepository.deleteById(id);
    }

}
