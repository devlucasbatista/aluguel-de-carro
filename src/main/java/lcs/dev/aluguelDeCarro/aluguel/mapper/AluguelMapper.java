package lcs.dev.aluguelDeCarro.aluguel.mapper;

import lcs.dev.aluguelDeCarro.aluguel.dto.AluguelDTO;
import lcs.dev.aluguelDeCarro.aluguel.model.AluguelModel;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import lcs.dev.aluguelDeCarro.cliente.repository.ClienteRepository;
import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import lcs.dev.aluguelDeCarro.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Component;

@Component
public class AluguelMapper {

    //INJEÇÃO DE DEPENDENCIA

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public AluguelMapper(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public AluguelDTO toDTO(AluguelModel model) {
        if (model == null) {
            return null;
        }
        return new AluguelDTO(
                model.getId(),
                model.getDataInicio(),
                model.getDataFim(),
                model.getValorTotal(),
                model.getStatus(),
                model.getCliente() != null ? model.getCliente().getId(): null,
                model.getVeiculo() != null ? model.getVeiculo().getId() : null
        );
    }

    public AluguelModel toModel(AluguelDTO dto) {
        if (dto == null) {
            return null;
        }

        VeiculoModel veiculo = veiculoRepository.findById(dto.getVeiculoId()).orElse(null);
        ClienteModel cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);

        return new AluguelModel(
                dto.getId(),
                veiculo,
                cliente,
                dto.getDataInicio(),
                dto.getDataFim(),
                dto.getValorTotal() != null ? dto.getValorTotal() : 0.0,
                dto.getStatus()
        );
    }

}
