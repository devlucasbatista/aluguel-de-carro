package lcs.dev.aluguelDeCarro.aluguel.mapper;

import lcs.dev.aluguelDeCarro.aluguel.dto.AluguelDTO;
import lcs.dev.aluguelDeCarro.aluguel.model.AluguelModel;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import lcs.dev.aluguelDeCarro.cliente.repository.ClienteRepository;
import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import lcs.dev.aluguelDeCarro.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Component;

// Componente responsável por converter AluguelModel (entidade) <-> AluguelDTO (objeto de transporte)
@Component
public class AluguelMapper {

    // INJEÇÃO DE DEPENDENCIA
    // repositórios usados para resolver cliente/veículo a partir dos ids recebidos no DTO

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    // Injeção de dependência via construtor
    public AluguelMapper(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    // Converte a entidade (vinda do banco) para o DTO (enviado na resposta da API)
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
                model.getCliente() != null ? model.getCliente().getId(): null, // envia só o id do cliente, não a entidade inteira
                model.getVeiculo() != null ? model.getVeiculo().getId() : null // envia só o id do veículo, não a entidade inteira
        );
    }

    // Converte o DTO (recebido na requisição) para a entidade (para salvar no banco)
    public AluguelModel toModel(AluguelDTO dto) {
        if (dto == null) {
            return null;
        }

        // busca as entidades completas de cliente e veículo a partir dos ids informados no DTO
        VeiculoModel veiculo = veiculoRepository.findById(dto.getVeiculoId()).orElse(null);
        ClienteModel cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);

        return new AluguelModel(
                dto.getId(),
                veiculo,
                cliente,
                dto.getDataInicio(),
                dto.getDataFim(),
                dto.getValorTotal() != null ? dto.getValorTotal() : 0.0, // evita valor nulo antes do cálculo feito no service
                dto.getStatus()
        );
    }

}
