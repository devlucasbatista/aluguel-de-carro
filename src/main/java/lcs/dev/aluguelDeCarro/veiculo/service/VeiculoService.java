package lcs.dev.aluguelDeCarro.veiculo.service;


import lcs.dev.aluguelDeCarro.exceptions.VeiculoNotFoundException;
import lcs.dev.aluguelDeCarro.veiculo.dto.VeiculoDTO;
import lcs.dev.aluguelDeCarro.veiculo.mapper.VeiculoMapper;
import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import lcs.dev.aluguelDeCarro.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Camada de serviço: concentra a regra de negócio de Veiculo entre o controller e o repositório
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoMapper veiculoMapper;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    // SALVAR VEICULO
    // Converte o DTO em entidade, força "disponivel = true" (todo veículo novo entra disponível) e persiste
    public VeiculoDTO salvarVeiculo(VeiculoDTO veiculoDTO) {
        VeiculoModel veiculoDisponivel = veiculoMapper.toModel(veiculoDTO);
        veiculoDisponivel.setDisponivel(true);
        return veiculoMapper.toDTO(veiculoRepository.save(veiculoDisponivel));
    }

    // LISTAR VEICULOS
    // Busca todos os registros e converte cada entidade em DTO
    public List<VeiculoDTO> listarVeiculos() {
        List<VeiculoModel> veiculos = veiculoRepository.findAll();
        return veiculos.stream().map(veiculoMapper::toDTO).collect(Collectors.toList());
    }
    // LISTAR VEICULO PELO SEU ID
    // Lança Veiculo NotFoundException se o id não existir

    public VeiculoDTO listarVeiculoPorId(Long id) {
        VeiculoModel veiculoModel = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));
        return veiculoMapper.toDTO(veiculoModel);
    }
    // ATUALIZAR VEICULO
    // Busca a entidade existente, sobrescreve os campos com os dados do DTO e salva novamente
    // (mantém o proprietário atual, pois esse campo não é alterado aqui)
    public VeiculoDTO atualizarVeiculoPorId(Long id, VeiculoDTO veiculoDTO) {
        VeiculoModel veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));
        veiculo.setValorDiaria(veiculoDTO.getValorDiaria());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setDisponivel(veiculoDTO.isDisponivel());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setCor(veiculoDTO.getCor());
        return veiculoMapper.toDTO(veiculoRepository.save(veiculo));
    }
    // DELETAR VEICULO
    public void excluirVeiculoPorId(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new VeiculoNotFoundException(id);
        }
        veiculoRepository.deleteById(id);
    }
}
